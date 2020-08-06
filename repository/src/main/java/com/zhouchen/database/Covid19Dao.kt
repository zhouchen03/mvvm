package com.zhouchen.database

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

    @Delete
    suspend fun delete(countyData: CovidZip)

    @Query("DELETE FROM CovidZip")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAllAndInsert(dbCovid19Data: DbCovid19Data) {
        delete(dbCovid19Data.covidZip)
        insert(dbCovid19Data.covidZip)
        insertList(dbCovid19Data.counties)
    }

    @Transaction
    @Query("SELECT * FROM CovidZip")
    suspend fun get(): DbCovid19Data?

    /**
     * Use this to observe DB changes
     */
    @Transaction
    @Query("SELECT * FROM CovidZip")
    fun getFlow(): Flow<DbCovid19Data?>
}