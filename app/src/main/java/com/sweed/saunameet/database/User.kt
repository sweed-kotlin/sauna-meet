package com.sweed.saunameet.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = User.TABLE_NAME)
data class User(
    @ColumnInfo(name = User.ID)
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0L,

    @ColumnInfo(name = User.NAME)
    val name: String,

    @ColumnInfo(name = User.PASSWORD)
    val password: String
) {

    companion object {
        const val TABLE_NAME = "user_table_name"
        const val ID = "id"
        const val NAME = "name"
        const val PASSWORD = "password"


    }

}