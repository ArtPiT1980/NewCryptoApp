package com.artpit.android.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinInfoDao {
//    @Query("select * from full_price_list order by lastUpdate desc")
    @Query("select * from full_price_list order by fromSymbol")
    fun getPriceList(): LiveData<List<CoinInfoDbModel>>

    @Query("select * from full_price_list where fromSymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinInfoDbModel>)
}