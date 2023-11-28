package com.artpit.android.cryptoapp.domain

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String,
    val price: Double,
    val lastUpdate: String,
    val imageUrl: String,
    val highDay: Double,
    val lowDay: Double,
    val lastMarket: String,
)