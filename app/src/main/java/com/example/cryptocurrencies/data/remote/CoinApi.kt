package com.example.cryptocurrencies.data.remote

import com.example.cryptocurrencies.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencies.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String): CoinDetailDto
}