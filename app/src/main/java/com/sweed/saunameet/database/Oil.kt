package com.sweed.saunameet.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// ingredients --> Drop down list with TextInput Field to Add new Items
// Ratings as Stars that fill up onclick

@Entity(tableName = Oil.TABLE_NAME)
data class Oil(
    @ColumnInfo(name = ID)
    @PrimaryKey(autoGenerate = true)
    val oilId: Long = 0L,

    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = RATING)
    val rating: Float,

    val isAddButton: Boolean = false,

//    val ingredients: List<String> = listOf(),

    @ColumnInfo(name = EFFECT)
    val effect: String = "Calm"
) {

    companion object {
        const val TABLE_NAME = "oil_table_name"
        const val ID = "id"
        const val NAME = "name"
        const val RATING = "rating"
        const val EFFECT = "effect"


    }
}