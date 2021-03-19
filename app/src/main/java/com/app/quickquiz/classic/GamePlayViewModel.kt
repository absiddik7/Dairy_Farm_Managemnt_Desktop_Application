package com.app.quickquiz.classic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.quickquiz.bookmarkDB.BookmarkDao
import com.app.quickquiz.bookmarkDB.BookmarkData
import kotlinx.coroutines.*

class GamePlayViewModel(
    private val categoryName: String,
    private val db: BookmarkDao
   // private val bookmarkDetails: BookmarkData,
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _allQS = MutableLiveData<ArrayList<QuestionDataJson>>()
    val allQS: LiveData<ArrayList<QuestionDataJson>>
        get() = _allQS

    fun insertBookmark() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

               // val data = bookmarkDetails
                //db.insert(data)

            }
        }
    }

    private suspend fun insert(bookmark: BookmarkData) {
        withContext(Dispatchers.IO) {
            db.insert(bookmark)
        }
    }

}