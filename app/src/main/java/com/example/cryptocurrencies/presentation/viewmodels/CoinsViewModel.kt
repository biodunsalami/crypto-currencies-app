package com.example.cryptocurrencies.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencies.common.Resource
import com.example.cryptocurrencies.domain.models.Coin
import com.example.cryptocurrencies.domain.models.CoinDetail
import com.example.cryptocurrencies.domain.usecases.GetCoinDetailsUseCase
import com.example.cryptocurrencies.domain.usecases.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase
) : ViewModel() {

    private val _coinList = MutableLiveData<Resource<List<Coin>>>()
    val coinList: LiveData<Resource<List<Coin>>> get() = _coinList

    private val _coinDetails = MutableLiveData<Resource<CoinDetail>>()
    val coinDetails: LiveData<Resource<CoinDetail>> get() = _coinDetails

    init {
        getCoins()

    }

    fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _coinList.value = result
                    Log.e("Result", result.data.toString())
                }
                is Resource.Success -> {
                    _coinList.value = result
                    Log.e("Result", result.data.toString())
                }
                is Resource.Error -> {
                    _coinList.value = result
                    Log.e("Result", result.data.toString())
                }

            }

        }.launchIn(viewModelScope)

    }


    fun getCoinDetails(coinId: String) {
        getCoinDetailsUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _coinDetails.value = result
                }
                is Resource.Success -> {
                    _coinDetails.value = result
                }
                is Resource.Error -> {
                    _coinDetails.value = result
                }
            }
        }
    }

}