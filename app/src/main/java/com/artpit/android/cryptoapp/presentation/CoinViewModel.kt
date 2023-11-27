package com.artpit.android.cryptoapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.artpit.android.cryptoapp.data.network.ApiFactory
import com.artpit.android.cryptoapp.data.database.AppDatabase
import com.artpit.android.cryptoapp.data.network.model.CoinInfoDto
import com.artpit.android.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinInfoDto> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    private fun loadData() {
        val disposable = ApiFactory.apiServide.getTopCoinsInfo(limit = 50)
            .map { it.names?.map { it.coinName?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiServide.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    db.coinPriceInfoDao().insertPriceList(it)
                    Log.d("TEST_OF_LOADING_DATA", "Success: $it")
                }
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message.toString()}")
            })
    }

    private fun getPriceListFromRawData(
        coinInfoJsonContainer: CoinInfoJsonContainerDto
    ): List<CoinInfoDto>? {
        val result = ArrayList<CoinInfoDto>()
        val jsonObject = coinInfoJsonContainer.json ?: return null
        val coinKeySet = jsonObject.keySet()

        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()

            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )

                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}