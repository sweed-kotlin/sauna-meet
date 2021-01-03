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
//    should be a list, for now strings concated

    @ColumnInfo(name = FAVORITE)
    var favorite: Boolean = false,

    @ColumnInfo(name = EFFECT)
    val effect: String = "Calm",

    @ColumnInfo(name = CREATED)
    val createdMillis: Long = System.currentTimeMillis(),

    @ColumnInfo(name = LAST_USED)
    var lastUsedMillis: Long? = null,

    ) {
    companion object {
        const val TABLE_NAME = "oil_table_name"
        const val ID = "id"
        const val NAME = "name"
        const val RATING = "rating"
        const val FAVORITE = "favorite"
        const val EFFECT = "effect"
        const val CREATED = "created_date"
        const val LAST_USED = "last_used"
    }
}