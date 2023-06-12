package com.example.cryptocurrencies.domain.usecases

import com.example.cryptocurrencies.common.Resource
import com.example.cryptocurrencies.domain.models.Coin
import com.example.cryptocurrencies.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> {
        return repository.getCoins()
    }
}