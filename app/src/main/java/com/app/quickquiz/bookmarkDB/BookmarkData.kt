package com.app.quickquiz.bookmarkDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_table")
data class BookmarkData(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name ="questions")
    val questions:String,

    @ColumnInfo(name = "bookmarkQS")
    val bookmarkQS:Boolean,

    @ColumnInfo(name = "right_ans")
    val correctAns:String

)

