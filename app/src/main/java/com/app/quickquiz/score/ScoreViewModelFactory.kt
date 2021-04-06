package com.app.quickquiz.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.quickquiz.database.ScoreDatabaseDao

@Suppress("UNCHECKED_CAST")
class ScoreViewModelFactory(
    private val dataSource: ScoreDatabaseDao,
    private val categoriesName: String,
    private val rightAns: Long,
    private val wrongAns: Long,
    private val indexNo:Int

) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(dataSource, categoriesName, rightAns, wrongAns,indexNo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}