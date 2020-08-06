package com.zhouchen.database

import androidx.annotation.NonNull
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Covid19KeyValueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keyValueData: StringKeyValuePair)

    @Query("SELECT * FROM StringKeyValuePair WHERE `key` = :key LIMIT 1")
    suspend fun get(@NonNull key: String): StringKeyValuePair?

    @Query("DELETE FROM StringKeyValuePair WHERE `key` = :key")
    suspend fun delete(@NonNull key: String)

    @Query("DELETE FROM StringKeyValuePair")
    suspend fun clear()

    @Query("SELECT * FROM StringKeyValuePair")
    suspend fun getAll(): List<StringKeyValuePair>?

}