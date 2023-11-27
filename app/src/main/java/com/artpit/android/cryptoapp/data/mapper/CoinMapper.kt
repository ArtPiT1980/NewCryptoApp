package com.artpit.android.cryptoapp.data.mapper

import com.artpit.android.cryptoapp.data.database.CoinInfoDbModel
import com.artpit.android.cryptoapp.data.network.model.CoinInfoDto
import com.artpit.android.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.artpit.android.cryptoapp.data.network.model.CoinNamesListDto
import com.artpit.android.cryptoapp.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {
    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel = CoinInfoDbModel(
            fromSymbol = dto.fromSymbol,
            toSymbol = dto.toSymbol,
            price = dto.price,
            lastUpdate = dto.lastUpdate,
            imageUrl = dto.imageUrl,
            highDay = dto.highDay,
            lowDay = dto.lowDay,
            lastMarket = dto.lastMarket
        )

    fun mapDbModelToDto(dbModel: CoinInfoDbModel): CoinInfoDto = CoinInfoDto(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastUpdate = dbModel.lastUpdate,
        imageUrl = dbModel.imageUrl,
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        lastMarket = dbModel.lastMarket
    )

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastUpdate = dbModel.lastUpdate,
        imageUrl = dbModel.imageUrl,
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        lastMarket = dbModel.lastMarket
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()

        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()

            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )

                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapCoinNamesListDtoToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map { it.coinName?.name }?.joinToString(",") ?: ""
    }
}