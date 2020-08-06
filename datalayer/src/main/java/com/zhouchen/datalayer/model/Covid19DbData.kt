package com.zhouchen.datalayer.model

import androidx.room.*

data class DbCovid19Data(
        @Embedded
        val covidZip: CovidZip,
        @Relation(parentColumn = "zipCd", entityColumn = "zipCode")
        val counties: List<CountyData>
)

@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
@Entity
data class CovidZip(
        @PrimaryKey
        val zipCd: String
)

@Entity(primaryKeys = ["countyName"])
data class CountyData(
        val zipCode: String,
        @Embedded
        val geo: GeoData,
        val countyName: String,
        val date: String,
        val deathCt: Int,
        val positiveCt: Int,
        val recoveredCt: Int?
)

@Entity(primaryKeys = ["rightTopLatLong", "leftBottomLatLong", "leftTopLatLong", "rightBottomLatLong"])
data class GeoData (
        val rightTopLatLong: Double,
        val leftBottomLatLong: Double,
        val leftTopLatLong: Double,
        val rightBottomLatLong: Double
)

