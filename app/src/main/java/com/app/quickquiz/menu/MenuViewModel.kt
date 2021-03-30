package com.app.quickquiz.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel: ViewModel() {

    private val _navigateToRulesFragment = MutableLiveData<Boolean?>()
    val navigateToRulesFragment: LiveData<Boolean?>
        get() = _navigateToRulesFragment

    fun doneRulesFragmentNavigation() {
        _navigateToRulesFragment.value = null
    }

    fun onNavigateToRulesFragment() {
        _navigateToRulesFragment.value = true
    }


}