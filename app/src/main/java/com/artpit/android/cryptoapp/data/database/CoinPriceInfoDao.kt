package com.artpit.android.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artpit.android.cryptoapp.data.model.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {
    @Query("select * from full_price_list order by lastUpdate desc")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    @Query("select * from full_price_list where fromSymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)
}