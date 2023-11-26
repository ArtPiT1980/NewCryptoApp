package com.artpit.android.cryptoapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.artpit.android.cryptoapp.data.network.ApiFactory
import com.artpit.android.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

@Entity(tableName = "full_price_list")
data class CoinPriceInfo(
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
) {
    fun getFormattedTime(): String {
        return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageUrl(): String {
        return ApiFactory.BASE_IMAGE_URL + imageUrl
    }
}