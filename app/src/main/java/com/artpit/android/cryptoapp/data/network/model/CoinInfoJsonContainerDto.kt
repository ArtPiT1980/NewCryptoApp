package com.artpit.android.cryptoapp.data.network.model


import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CoinInfoJsonContainerDto(
    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)