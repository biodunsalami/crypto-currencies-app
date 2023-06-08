package com.example.cryptocurrencies.domain.models

import com.example.cryptocurrencies.data.remote.response.*

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMembers>
)
