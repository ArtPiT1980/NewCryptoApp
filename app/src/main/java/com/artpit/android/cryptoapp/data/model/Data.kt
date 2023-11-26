package com.artpit.android.cryptoapp.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null
)