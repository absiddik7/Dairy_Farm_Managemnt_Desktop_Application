package com.app.quickquiz.bookmarkDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.quickquiz.database.CategoriesScore

@Dao
interface BookmarkDao {

    @Insert
    fun insert(bookmark: BookmarkData)

    @Query("SELECT questions FROM bookmark_table ORDER BY id DESC")
    fun getAllQS():Array<String>

    @Query("SELECT EXISTS (SELECT 1 FROM bookmark_table WHERE questions = :qs)")
    fun exists(qs: String): Boolean?

    @Query("SELECT * from bookmark_table WHERE bookmarkQS == :isBookmarked ORDER BY id DESC")
    fun getAllBookmark(isBookmarked:Boolean): List<BookmarkData>

}