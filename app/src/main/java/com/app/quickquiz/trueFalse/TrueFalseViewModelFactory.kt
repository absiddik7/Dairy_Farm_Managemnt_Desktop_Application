package com.app.quickquiz.trueFalse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.quickquiz.database.ScoreDatabaseDao

class TrueFalseViewModelFactory(
    private val categoryName: String,
    private val dataSource: ScoreDatabaseDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrueFalseViewModel::class.java)) {
            return TrueFalseViewModel(categoryName, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}