package com.artpit.android.cryptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinInfoListUseCase(private val repository: CoinRepository) {
    operator fun invoke(): LiveData<List<CoinInfo>> = repository.getCoinInfoList()
}