package com.zhouchen.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.zhouchen.datalayer.model.Sample


@Dao
interface SampleDao {
    @get:Query("SELECT * FROM Sample")
    val all: List<Sample>

    @Query("DELETE FROM Sample")
    fun nukeTable()

    @Insert
    fun insert(vararg users: Sample)
    
    @Transaction
    @Insert
    fun insertAll(samples: List<Sample>)
}