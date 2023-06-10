package com.example.cryptocurrencies.data.di

import com.example.cryptocurrencies.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencies.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsCoinRepository(coinRepositoryImpl: CoinRepositoryImpl): CoinRepository

}