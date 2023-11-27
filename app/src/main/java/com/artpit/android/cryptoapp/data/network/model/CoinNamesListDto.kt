package com.artpit.android.cryptoapp.data.network.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNamesListDto(
    @SerializedName("CoinNameContainerDto")
    @Expose
    val names: List<CoinNameContainerDto>? = null,
)