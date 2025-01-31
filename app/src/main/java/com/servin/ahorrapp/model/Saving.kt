package com.servin.ahorrapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saving")
data class Saving(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val userId: Int=0,

    @ColumnInfo
    val game:String="",

    @ColumnInfo
    val date: String="",

)
