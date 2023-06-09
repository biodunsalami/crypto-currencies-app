package com.example.cryptocurrencies.domain.repository

import com.example.cryptocurrencies.common.Resource
import com.example.cryptocurrencies.domain.models.Coin
import com.example.cryptocurrencies.domain.models.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<Resource<List<Coin>>>

    fun getCoinDetails(coinId: String): Flow<Resource<CoinDetail>>
}