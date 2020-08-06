package com.zhouchen.network

data class CovidApiData(
    val zipCd: String,
    val counties: List<County>
)

data class County(
        val countyName: String,
        val geo: Geo,
        val historicData: List<Historic>
)

data class Geo (
        val rightTopLatLong: Double,
        val leftBottomLatLong: Double,
        val leftTopLatLong: Double,
        val rightBottomLatLong: Double
)

data class Historic(
        val date: String,
        val deathCt: Int,
        val positiveCt: Int,
        val recoveredCt: Int?
)
