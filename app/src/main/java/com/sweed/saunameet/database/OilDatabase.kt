package com.sweed.saunameet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


const val DB_NAME = "tracking_database"
const val DB_VERSION = 1


@Database(entities = [Oil::class], version = DB_VERSION, exportSchema = false)
abstract class OilDatabase: RoomDatabase() {

    abstract val oilDatabaseDao: OilDatabaseDao

    // The companion object allows clients to access the methods for creating or getting the
// database without instantiating the class. Since the only purpose of this class is to
// provide a database, there is no reason to ever instantiate it.
    companion object {
        @Volatile
        private var INSTANCE: OilDatabase? = null
        fun getInstance(context: Context): OilDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        OilDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance


            }

        }
    }

}