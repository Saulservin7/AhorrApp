package com.servin.ahorrapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "usuarios")
data class Users(
    @PrimaryKey(autoGenerate = true)
    val int: Int =0,

    @ColumnInfo
    val name: String="",

    @ColumnInfo
    val email : String?="",

    @ColumnInfo
    val password: String?=""

)
