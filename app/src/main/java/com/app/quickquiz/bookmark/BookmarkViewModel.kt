package com.app.quickquiz.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.quickquiz.bookmarkDB.BookmarkDao
import com.app.quickquiz.bookmarkDB.BookmarkData
import kotlinx.coroutines.*

class BookmarkViewModel(private val db: BookmarkDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _allBookmark = MutableLiveData<List<BookmarkData>>()
    val allBookmark: LiveData<List<BookmarkData>>
        get() = _allBookmark

    fun getAllBookmarked(){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                _allBookmark.postValue(db.getAllBookmark(true))
            }
        }
    }
}