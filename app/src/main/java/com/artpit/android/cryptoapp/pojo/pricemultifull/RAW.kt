package com.artpit.android.cryptoapp.pojo.pricemultifull


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RAW(
    @SerializedName("BTC")
    @Expose
    val btc: BTCX
)