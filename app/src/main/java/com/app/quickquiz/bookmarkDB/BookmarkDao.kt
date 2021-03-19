package com.app.quickquiz.bookmarkDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkDao {

    @Insert
    fun insert(bookmark: BookmarkData)

    @Query("SELECT questions FROM bookmark_table ORDER BY id DESC")
    fun getAllQS():Array<String>

    @Query("SELECT EXISTS (SELECT 1 FROM bookmark_table WHERE questions = :qs)")
    fun exists(qs: String): Boolean?

}