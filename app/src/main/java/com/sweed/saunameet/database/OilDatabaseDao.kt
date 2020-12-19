package com.sweed.saunameet.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface OilDatabaseDao {

    @Insert
    suspend fun insert(oil: Oil)

    @Update
    suspend fun update(oil: Oil)

    @Query("SELECT * from ${Oil.TABLE_NAME} WHERE ${Oil.ID} = :key")
    suspend fun get(key: Long): Oil?

    @Query("SELECT * from ${Oil.TABLE_NAME} WHERE ${Oil.NAME} = :name")
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