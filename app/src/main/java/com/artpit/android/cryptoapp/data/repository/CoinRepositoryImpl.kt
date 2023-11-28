package com.artpit.android.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.artpit.android.cryptoapp.data.database.AppDatabase
import com.artpit.android.cryptoapp.data.mapper.CoinMapper
import com.artpit.android.cryptoapp.data.workers.RefreshDataWorker
import com.artpit.android.cryptoapp.domain.CoinInfo
import com.artpit.android.cryptoapp.domain.CoinRepository

class CoinRepositoryImpl(private val application: Application) : CoinRepository {
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )

    }
}