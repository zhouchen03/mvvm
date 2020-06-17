package com.zhouchen.repository.di.module

import android.content.Context
import androidx.room.Room
import com.zhouchen.database.SampleDao
import com.zhouchen.database.SampleDatabase
import dagger.Module
import dagger.Provides

/**
 * Module which provides all required dependencies about network
 */

@Module
object DatabaseModule {

    @Provides
    internal fun provideSampleDao(context: Context): SampleDao {
        val db = Room.databaseBuilder(context, SampleDatabase::class.java, "samples").build()
        return db.sampleDao()
    }

}