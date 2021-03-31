package com.app.quickquiz.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel: ViewModel() {

    private val _navigateToRulesFragment = MutableLiveData<Boolean?>()
    val navigateToRulesFragment: LiveData<Boolean?>
        get() = _navigateToRulesFragment

    private val _navigateToAboutUsFragment = MutableLiveData<Boolean?>()
    val navigateToAboutUsFragment: LiveData<Boolean?>
        get() = _navigateToAboutUsFragment



    fun doneRulesFragmentNavigation() {
        _navigateToRulesFragment.value = null
    }

    fun onNavigateToRulesFragment() {
        _navigateToRulesFragment.value = true
    }

    fun doneAboutUsFragmentNavigation() {
        _navigateToAboutUsFragment.value = null
    }

    fun onNavigateToAboutUsFragment() {
        _navigateToAboutUsFragment.value = true
    }


}