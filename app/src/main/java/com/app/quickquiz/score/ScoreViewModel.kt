package com.app.quickquiz.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.quickquiz.database.CategoriesScore
import com.app.quickquiz.database.ScoreDatabaseDao
import kotlinx.coroutines.*

class ScoreViewModel(
    private val db: ScoreDatabaseDao,
    private val categoryName: String,
    private val rightScore: Long,
    private val wrongScore: Long,
    private var indexNo: Int,
    private val qsArraySize: Int,

    ) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val catImg:Int = 0

    private fun setScore() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val existsOrNot = db.exists(categoryName)!!
                val highScore = rightScore

                if (!existsOrNot) {
                    val newScore =
                        CategoriesScore(0,
                            categoryName,
                            catImg,
                            rightScore,
                            wrongScore,
                            highScore,
                            indexNo,
                            0)
                    insert(newScore)
                } else {
                    onUpdateScore()
                }
            }
        }
    }

    private fun onUpdateScore() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val allScore = db.getRightScore(categoryName)
                val correctScore = allScore.correctAns + rightScore
                val wrongScore = allScore.wrongAns + wrongScore
                var highScore = allScore.highScore
                val index = allScore.indexNo
                val oldIndex = allScore.oldIndex

                if (rightScore > highScore) {
                    highScore = rightScore
                }
                db.updateScore(categoryName, correctScore, wrongScore, highScore, indexNo, oldIndex)

                if (indexNo == qsArraySize) {
                    if (indexNo > oldIndex) {
                        val oldindex = indexNo
                        db.updateScore(categoryName,
                            correctScore,
                            wrongScore,
                            highScore,
                            indexNo,
                            oldindex
                        )
                    }
                }

                if (oldIndex != 0) {
                    if (qsArraySize > oldIndex) {
                        if (index < oldIndex) {
                            db.updateScore(categoryName,
                                correctScore,
                                wrongScore,
                                highScore,
                                oldIndex,
                                oldIndex
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun insert(score: CategoriesScore) {
        withContext(Dispatchers.IO) {
            db.insert(score)
        }
    }

    fun setDataToDB(it: Boolean) {
        if (it) {
            setScore()
        }
    }

    private val _navigateToHome = MutableLiveData<Boolean?>()
    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome

    private val _navigateToProfile = MutableLiveData<Boolean?>()
    val navigateToProfile: LiveData<Boolean?>
        get() = _navigateToProfile

    private val _navigateFromPlayAgain = MutableLiveData<Boolean?>()
    val navigateFromPlayAgain: LiveData<Boolean?>
        get() = _navigateFromPlayAgain

    fun onNavigateToHome() {
        _navigateToHome.value = true
    }

    fun onNavigateToProfile() {
        _navigateToProfile.value = true

    }

    fun onNavigateFromPlayAgain() {
        _navigateFromPlayAgain.value = true
    }

    fun doneHomeNavigation() {
        _navigateToHome.value = null
    }

    fun doneProfileNavigation() {
        _navigateToProfile.value = null
    }

    fun doneNavigateFromPlayAgain() {
        _navigateFromPlayAgain.value = null
    }

}