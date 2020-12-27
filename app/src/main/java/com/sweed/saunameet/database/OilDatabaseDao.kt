package com.sweed.saunameet.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OilDatabaseDao {

    @Insert
    suspend fun insert(oil: Oil)

    @Update
    suspend fun update(oil: Oil)

    @Query("SELECT * from ${Oil.TABLE_NAME} WHERE ${Oil.ID} = :key ORDER BY id DESC LIMIT 1")
    fun get(key: Long): LiveData<Oil>

    @Query("SELECT * from ${Oil.TABLE_NAME} WHERE ${Oil.ID} = :key ORDER BY id DESC LIMIT 1")
    fun getById(key: Long): Oil?

    @Query("DELETE FROM ${Oil.TABLE_NAME} WHERE ${Oil.ID} = :key")
    fun deleteById(key: Long)

    @Delete
    suspend fun delete(oil: Oil)

    @Query("SELECT * from ${Oil.TABLE_NAME} WHERE ${Oil.NAME} = :name ORDER BY id DESC LIMIT 1")
    suspend fun getOilByName(name: String): Oil?

    //    /**
//     * Selects and returns the night with given nightId.
//     */
//    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :key")
//    fun getNightWithId(key: Long): LiveData<SleepNight>
//
//    @Query("DELETE FROM daily_sleep_quality_table")
//    suspend fun clear()
//
//    // Add a @Query with a getTonight() function. Make the SleepNight returned by
//// getTonight() nullable, so that the function can handle the case where the table is empty.
//// (The table is empty at the beginning, and after the data is cleared.)
//    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
//    suspend fun getTonight(): SleepNight?
//
    @Query("SELECT * FROM ${Oil.TABLE_NAME} ORDER BY isAddButton DESC, ${Oil.ID} DESC")
    fun getAllOils(): LiveData<List<Oil>>

}