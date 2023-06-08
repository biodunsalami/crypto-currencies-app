package com.example.cryptocurrencies.data.remote

import com.example.cryptocurrencies.data.remote.response.CoinDetailResponse
import com.example.cryptocurrencies.data.remote.response.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinResponse>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String): CoinDetailResponse
}