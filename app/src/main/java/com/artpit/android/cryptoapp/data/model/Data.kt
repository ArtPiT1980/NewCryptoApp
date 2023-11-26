package com.artpit.android.cryptoapp.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CoinName")
    @Expose
    val coinName: CoinName? = null
)