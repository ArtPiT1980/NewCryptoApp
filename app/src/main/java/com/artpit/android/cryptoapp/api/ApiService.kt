package com.artpit.android.cryptoapp.api

import com.artpit.android.cryptoapp.pojo.pricemultifull.CoinPriceInfoRawData
import com.artpit.android.cryptoapp.pojo.totalvolfull.CoinInfoListOfData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"

        private const val CURRENCY = "USD"
        private const val COIN = "BTC,ETH,EOS"
    }

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY)
        apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT)
        limit: Int = 100,
        @Query(QUERY_PARAM_TO_SYMBOL)
        tSym: String = CURRENCY,
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY)
        apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOLS)
        fSyms: String = COIN,
        @Query(QUERY_PARAM_TO_SYMBOLS)
        tSyms: String = CURRENCY,
    ): Single<CoinPriceInfoRawData>
}