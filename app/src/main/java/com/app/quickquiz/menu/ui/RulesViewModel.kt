package com.app.quickquiz.menu.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RulesViewModel : ViewModel() {

    private val _navigateToMenuFragment = MutableLiveData<Boolean?>()
    val navigateToMenuFragment: LiveData<Boolean?>
        get() = _navigateToMenuFragment

    private val _navigateToClassic = MutableLiveData<Boolean?>()
    val navigateToClassic: LiveData<Boolean?>
        get() = _navigateToClassic

    private val _navigateToArcade = MutableLiveData<Boolean?>()
    val navigateToArcade: LiveData<Boolean?>
        get() = _navigateToArcade

    private val _navigateToCategory = MutableLiveData<Boolean?>()
    val navigateToCategory: LiveData<Boolean?>
        get() = _navigateToCategory

    fun onNavigateToMenuFragment() {
        _navigateToMenuFragment.value = true
    }

    fun onNavigateToClassic() {
        _navigateToClassic.value = true
    }

    fun onNavigateToArcade() {
        _navigateToArcade.value = true
    }

    fun onNavigateToCategory() {
        _navigateToCategory.value = true
    }


    fun doneMenuFragmentNavigation() {
        _navigateToMenuFragment.value = null
    }

    fun doneClassicNavigation() {
        _navigateToClassic.value = null
    }

    fun doneArcadeNavigation() {
        _navigateToArcade.value = null
    }

    fun doneCategoryNavigation() {
        _navigateToCategory.value = null
    }



}