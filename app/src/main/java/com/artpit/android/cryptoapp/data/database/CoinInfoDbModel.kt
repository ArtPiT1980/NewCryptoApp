package com.artpit.android.cryptoapp.data.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.artpit.android.cryptoapp.data.network.ApiFactory
import com.artpit.android.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String,
    val price: Double,
    val lastUpdate: Long?,
    val imageUrl: String,
    val highDay: Double,
    val lowDay: Double,
    val lastMarket: String,
) {
    fun getFormattedTime(): String {
        return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageUrl(): String {
        return ApiFactory.BASE_IMAGE_URL + imageUrl
    }
}