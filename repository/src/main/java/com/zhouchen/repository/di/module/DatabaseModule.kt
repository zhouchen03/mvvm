package com.zhouchen.repository.di.module

import android.content.Context
import androidx.room.Room
import com.zhouchen.database.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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

    @Provides
    fun provideCovid19DB(context: Context): Covid19Database {
        return Room.databaseBuilder(context, Covid19Database::class.java, "covid19")
            .build()
    }

    @Provides
    internal fun provideCovid19Dao(covid19Database: Covid19Database): Covid19Dao {
        return covid19Database.covid19Dao()
    }

    @Provides
    internal fun provideKeyValueDao(covid19Database: Covid19Database): Covid19KeyValueDao {
        return covid19Database.covid19KeyValueDao()
    }
}