package com.app.quickquiz.arcade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.quickquiz.database.ScoreDatabaseDao

class ArcadeViewModelFactory(
    private val categoriesName:String,
    private val db: ScoreDatabaseDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArcadeViewModel::class.java)){
            return ArcadeViewModel(categoriesName,db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}