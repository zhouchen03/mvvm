package com.zhouchen.database

import androidx.annotation.NonNull
import androidx.room.*
import com.zhouchen.datalayer.model.CountyData
import com.zhouchen.datalayer.model.CovidZip
import com.zhouchen.datalayer.model.DbCovid19Data
import kotlinx.coroutines.flow.Flow

@Dao
interface Covid19Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countyData: CovidZip)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(listCounty: List<CountyData>)

    @Transaction
    @Query("DELETE FROM CovidZip WHERE `zipCd` = :zip")
    suspend fun delete(@NonNull zip: String)

    @Transaction
    suspend fun deleteAllAndInsert(dbCovid19Data: DbCovid19Data) {
        delete(dbCovid19Data.covidZip.zipCd)
        insert(dbCovid19Data.covidZip)
        insertList(dbCovid19Data.counties)
    }

    @Transaction
    @Query("SELECT * FROM CovidZip WHERE `zipCd` = :zip LIMIT 1")
    suspend fun get(@NonNull zip: String): DbCovid19Data?

    /**
     * Use this to observe DB changes
     */
    @Transaction
    @Query("SELECT * FROM CovidZip WHERE `zipCd` = :zip LIMIT 1")
    fun getFlow(@NonNull zip: String): Flow<DbCovid19Data?>
}