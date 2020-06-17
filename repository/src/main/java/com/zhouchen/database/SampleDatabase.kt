package com.zhouchen.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhouchen.datalayer.model.Sample

@Database(entities = [Sample::class], version = 1)
abstract class SampleDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao
}