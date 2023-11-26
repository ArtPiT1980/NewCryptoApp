package com.artpit.android.cryptoapp.pojo.totalvolfull


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoListOfData(
    @SerializedName("Data")
    @Expose
    val data: List<Data>? = null,
//    @SerializedName("Message")
//    @Expose
//    val message: String,
//    @SerializedName("Type")
//    @Expose
//    val type: Int
)