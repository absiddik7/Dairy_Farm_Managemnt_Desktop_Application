package com.app.quickquiz.arcade.quizFilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizFilterViewModel : ViewModel() {

    private val _navigateQuizFilterToHome = MutableLiveData<Boolean?>()
    val navigateQuizFilterToHome: LiveData<Boolean?>
        get() = _navigateQuizFilterToHome

    fun onNavigateQuizFilterToHome() {
        _navigateQuizFilterToHome.value = true
    }

    fun doneHomeNavigation() {
        _navigateQuizFilterToHome.value = null
    }

    private val _navigateToTimeTrial = MutableLiveData<Boolean?>()
    val navigateToTimeTrial: LiveData<Boolean?>
        get() = _navigateToTimeTrial

    fun onNavigateToTimeTrial() {
        _navigateToTimeTrial.value = true
    }

    fun doneTimeTrialNavigation() {
        _navigateToTimeTrial.value = null
    }

}