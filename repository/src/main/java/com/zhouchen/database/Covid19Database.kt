package com.zhouchen.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhouchen.datalayer.model.CountyData
import com.zhouchen.datalayer.model.CovidZip

@Database(
    entities = [CovidZip::class, CountyData::class,
        StringKeyValuePair::class], version = 1
)
abstract class Covid19Database : RoomDatabase() {
    abstract fun covid19Dao(): Covid19Dao
    abstract fun covid19KeyValueDao(): Covid19KeyValueDao
}