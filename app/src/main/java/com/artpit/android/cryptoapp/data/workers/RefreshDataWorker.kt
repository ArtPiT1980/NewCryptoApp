package com.artpit.android.cryptoapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.artpit.android.cryptoapp.data.database.AppDatabase
import com.artpit.android.cryptoapp.data.mapper.CoinMapper
import com.artpit.android.cryptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val coinInfoDao = AppDatabase.getInstance(context).coinPriceInfoDao()
    private val mapper = CoinMapper()

    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

    override suspend fun doWork(): Result {
        while (true) {
//            Log.d("loadDataFromServer", "doWork")
            try {
                val topCoins = ApiFactory.apiService.getTopCoinsInfo(limit = 50)
                val fromSymbols = mapper.mapCoinNamesListDtoToString(topCoins)
                val jsonContainer = ApiFactory.apiService.getFullPriceList(fSyms = fromSymbols)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val coinInfoDbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(coinInfoDbModelList)
            } catch (e: Exception) {
//                Log.e("loadDataFromServer", e.toString())
            }

            delay(10000)
        }
    }
}