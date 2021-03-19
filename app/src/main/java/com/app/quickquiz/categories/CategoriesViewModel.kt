package com.app.quickquiz.categories

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoriesViewModel : ViewModel() {

    private val _navigateToHome = MutableLiveData<Boolean?>()
    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome

    fun onNavigateToHome() {
        _navigateToHome.value = true
    }

    fun doneHomeNavigation() {
        _navigateToHome.value = null
    }
}