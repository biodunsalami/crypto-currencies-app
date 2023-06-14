package com.example.cryptocurrencies.data.remote.response

import com.example.cryptocurrencies.domain.models.CoinDetail
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinDetailResponse(
    val description: String,
    @Json(name = "development_status")
    val developmentStatus: String,
    @Json(name = "first_data_at")
    val firstDataAt: String,
    @Json(name = "hardware_wallet")
    val hardwareWallet: Boolean,
    @Json(name = "hash_algorithm")
    val hashAlgorithm: String,
    val id: String,
    @Json(name = "is_active")
    val isActive: Boolean,
    @Json(name = "is_new")
    val isNew: Boolean,
    @Json(name = "last_data_at")
    val lastDataAt: String,
    val links: Links,
    @Json(name = "links_extended")
    val linksExtended: List<LinksExtended>,
    val logo: String,
    val message: String,
    val name: String,
    @Json(name = "open_source")
    val openSource: Boolean,
    @Json(name = "org_structure")
    val orgStructure: String,
    @Json(name = "proof_type")
    val proofType: String,
    val rank: Int,
    @Json(name = "started_at")
    val startedAt: String,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<TeamMembers>,
    val type: String,
    val whitepaper: Whitepaper
)

fun CoinDetailResponse.toCoinDetails(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags.map { it.name } ,
        team = team
    )
}