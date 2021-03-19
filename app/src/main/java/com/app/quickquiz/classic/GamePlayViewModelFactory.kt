package com.app.quickquiz.classic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.quickquiz.bookmarkDB.BookmarkDao
import com.app.quickquiz.bookmarkDB.BookmarkData


class GamePlayViewModelFactory(
    private val categoryName: String,
    private val dataSource:BookmarkDao
    //private val bookmarkDetails: BookmarkData
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GamePlayViewModel::class.java)) {
            return GamePlayViewModel(categoryName,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}