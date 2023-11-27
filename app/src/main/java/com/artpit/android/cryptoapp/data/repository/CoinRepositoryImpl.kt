package com.artpit.android.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.artpit.android.cryptoapp.data.database.AppDatabase
import com.artpit.android.cryptoapp.data.mapper.CoinMapper
import com.artpit.android.cryptoapp.data.network.ApiFactory
import com.artpit.android.cryptoapp.data.network.ApiService
import com.artpit.android.cryptoapp.domain.CoinInfo
import com.artpit.android.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(private val application: Application) : CoinRepository {
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiServide
    private val mapper = CoinMapper()
    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        coinInfoDao.getPriceList().map { coinInfoList ->
            coinInfoList.map {
                mapper.mapDbModelToEntity(it)
            }
        }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }

    override suspend fun loadData() {
        while (true) {
            val topCoins = ApiFactory.apiServide.getTopCoinsInfo(limit = 50)
            val fromSymbols = mapper.mapCoinNamesListDtoToString(topCoins)
            val jsonContainer = ApiFactory.apiServide.getFullPriceList(fSyms = fromSymbols)
            val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
            val coinInfoDbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
            coinInfoDao.insertPriceList(coinInfoDbModelList)
            delay(10000)
        }
    }
}