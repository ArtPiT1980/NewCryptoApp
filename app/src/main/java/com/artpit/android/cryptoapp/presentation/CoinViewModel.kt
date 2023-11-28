package com.artpit.android.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.artpit.android.cryptoapp.data.repository.CoinRepositoryImpl
import com.artpit.android.cryptoapp.domain.CoinInfo
import com.artpit.android.cryptoapp.domain.GetCoinInfoListUseCase
import com.artpit.android.cryptoapp.domain.GetCoinInfoUseCase
import com.artpit.android.cryptoapp.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CoinRepositoryImpl(application)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fromSymbols: String): LiveData<CoinInfo> = getCoinInfoUseCase(fromSymbols)
}