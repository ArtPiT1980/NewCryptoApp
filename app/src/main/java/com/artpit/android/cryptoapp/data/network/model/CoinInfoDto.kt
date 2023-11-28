package com.artpit.android.cryptoapp.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDto(
    @SerializedName("FROMSYMBOL")
    @Expose
    @PrimaryKey
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String,
    @SerializedName("PRICE")
    @Expose
    val price: Double,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long?,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String,
    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double,
    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double,
    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String,
)