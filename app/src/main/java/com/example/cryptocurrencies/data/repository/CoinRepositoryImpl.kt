package com.example.cryptocurrencies.data.repository

import com.example.cryptocurrencies.common.Resource
import com.example.cryptocurrencies.data.remote.CoinApi
import com.example.cryptocurrencies.data.remote.response.toCoin
import com.example.cryptocurrencies.data.remote.response.toCoinDetails
import com.example.cryptocurrencies.domain.models.Coin
import com.example.cryptocurrencies.domain.models.CoinDetail
import com.example.cryptocurrencies.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {
    override fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())

            val coins = api.getCoins().map { it.toCoin() }

            emit(Resource.Success(coins))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {
            emit(Resource.Error(message = "An error occurred, check your internet connection"))
        }

    }

    override fun getCoinDetails(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())

            val coinDetails = api.getCoinDetails(coinId).toCoinDetails()

            emit(Resource.Success(coinDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {
            emit(Resource.Error(message = "An error occurred, check your internet connection"))
        }
    }
}