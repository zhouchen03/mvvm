package com.zhouchen.result

import androidx.annotation.WorkerThread
import com.zhouchen.datalayer.model.*
import com.zhouchen.network.CovidApiData

class Covid19Mapper(private val apiData: CovidApiData) : Mapper<DbCovid19Data> {

    @WorkerThread
    override fun getMapping(): DbCovid19Data {
        val list = ArrayList<CountyData>()
        apiData.counties.forEach {county ->
            county.historicData.forEach { historic ->
                list.add(CountyData(apiData.zipCd,
                    GeoData(
                        county.geo.rightTopLatLong,
                        county.geo.leftBottomLatLong,
                        county.geo.leftTopLatLong,
                        county.geo.rightBottomLatLong
                    ),
                    county.countyName,
                    historic.date,
                    historic.deathCt,
                    historic.positiveCt,
                    historic.recoveredCt))
            }
        }
        return DbCovid19Data(CovidZip(apiData.zipCd), list)
    }

}