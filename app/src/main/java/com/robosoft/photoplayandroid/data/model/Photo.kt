package com.robosoft.photoplayandroid.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @ColumnInfo(name = "avg_color")
    val avgColor: String?=null,
    @ColumnInfo(name = "height")
    val height: Int,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "photographer")
    val photographer: String,
    @ColumnInfo(name = "photographerId")
    val photographer_id: Int,
    @ColumnInfo(name = "photographerUrl")
    val photographer_url: String,
    @Embedded
    val src: Src,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "width")
    val width: Int
)