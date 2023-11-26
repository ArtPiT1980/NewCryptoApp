package com.artpit.android.cryptoapp.data.model


import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CoinPriceInfoRawData(
    @SerializedName("RAW")
    @Expose
    val coinPriceInfoJsonObject: JsonObject? = null
)