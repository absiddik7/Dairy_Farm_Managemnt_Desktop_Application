package com.app.quickquiz.arcade

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.quickquiz.database.ScoreDatabaseDao
import kotlinx.coroutines.*

class ArcadeViewModel(
    private val categoryName: String,
    private val db: ScoreDatabaseDao
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _indexNo = MutableLiveData<Int>()
    val indexNo: LiveData<Int>
        get() = _indexNo

    fun getIndex() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val categoryExistence = db.exists(categoryName)!!
                if(categoryExistence) {
                    val data = db.getScorePerCategory(categoryName)
                    _indexNo.postValue(data.indexNo)
                }
            }
        }
    }



}