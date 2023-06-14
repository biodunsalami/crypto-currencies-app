package com.example.cryptocurrencies.data.remote.response

import com.example.cryptocurrencies.domain.models.Coin
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CoinResponse(
    val id: String,
    @Json(name ="is_active")
    val isActive: Boolean,
    @Json(name = "is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinResponse.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )

}