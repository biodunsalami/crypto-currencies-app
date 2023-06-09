package com.example.cryptocurrencies.domain.usecases

import com.example.cryptocurrencies.common.Resource
import com.example.cryptocurrencies.domain.models.CoinDetail
import com.example.cryptocurrencies.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>>{
        return repository.getCoinDetails(coinId)
    }

}