package com.app.quickquiz.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.quickquiz.database.CategoriesScore
import com.app.quickquiz.database.ScoreDatabaseDao
import kotlinx.coroutines.*

class ProfileViewModel(private val db: ScoreDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _allCorrectScoreSum = MutableLiveData<Long>()
    val allCorrectScoreSum: LiveData<Long>
        get() = _allCorrectScoreSum

    private val _allWrongScoreSum = MutableLiveData<Long>()
    val allWrongScoreSum: LiveData<Long>
        get() = _allWrongScoreSum

    private val _averageAccuracy = MutableLiveData<Double>()
    val averageAccuracy: LiveData<Double>
        get() = _averageAccuracy

    private val _totalAnsweredQS = MutableLiveData<Long>()
    val totalAnsweredQS: LiveData<Long>
        get() = _totalAnsweredQS

    private val _classicCorrectScore = MutableLiveData<Long>()
    val classicCorrectScore: LiveData<Long>
        get() = _classicCorrectScore

    private val _classicWrongScore = MutableLiveData<Long>()
    val classicWrongScore: LiveData<Long>
        get() = _classicWrongScore

    private val _classicHighScore = MutableLiveData<Long>()
    val classicHighScore: LiveData<Long>
        get() = _classicHighScore

    private val _classicTotalAnsweredQS = MutableLiveData<Long>()
    val classicTotalAnsweredQS: LiveData<Long>
        get() = _classicTotalAnsweredQS

    private val _classicAccuracy = MutableLiveData<Double>()
    val classicAccuracy: LiveData<Double>
        get() = _classicAccuracy

    private val _arcadeCorrectScore = MutableLiveData<Long>()
    val arcadeCorrectScore: LiveData<Long>
        get() = _arcadeCorrectScore

    private val _arcadeWrongScore = MutableLiveData<Long>()
    val arcadeWrongScore: LiveData<Long>
        get() = _arcadeWrongScore

    private val _arcadeHighScore = MutableLiveData<Long>()
    val arcadeHighScore: LiveData<Long>
        get() = _arcadeHighScore

    private val _arcadeTotalAnsweredQS = MutableLiveData<Long>()
    val arcadeTotalAnsweredQS: LiveData<Long>
        get() = _arcadeTotalAnsweredQS

    private val _arcadeAccuracy = MutableLiveData<Double>()
    val arcadeAccuracy: LiveData<Double>
        get() = _arcadeAccuracy

    private var _allScores = MutableLiveData<List<CategoriesScore>>()
    val allScores: LiveData<List<CategoriesScore>>
        get() = _allScores


    fun getAllScoreSum() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                val allCorrectScoreSum = db.allCorrectScoreSum()
                val allWrongScoreSum = db.allWrongScoreSum()
                val totalAnsweredQS = allCorrectScoreSum + allWrongScoreSum
                val scoreInPercentage = (allCorrectScoreSum.toDouble() / totalAnsweredQS) * 100

                _allCorrectScoreSum.postValue(allCorrectScoreSum)
                _allWrongScoreSum.postValue(allWrongScoreSum)
                _totalAnsweredQS.postValue(totalAnsweredQS)
                _averageAccuracy.postValue(scoreInPercentage)
            }
        }
    }

    fun getClassicScore() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                val categoryExistence = db.exists("Classic")!!
                if(categoryExistence){
                    val classicScore = db.getScorePerCategory("Classic")
                    val correctScore = classicScore.correctAns
                    val wrongScore = classicScore.wrongAns
                    val highScore = classicScore.highScore
                    val totalAnsweredQS = correctScore + wrongScore
                    val accuracy = (correctScore.toDouble() / totalAnsweredQS) * 100

                    _classicCorrectScore.postValue(correctScore)
                    _classicWrongScore.postValue(wrongScore)
                    _classicHighScore.postValue(highScore)
                    _classicTotalAnsweredQS.postValue(totalAnsweredQS)
                    _classicAccuracy.postValue(accuracy)
                }
            }
        }
    }

    fun getArcadeScore() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                val categoryExistence = db.exists("Arcade")!!
                if(categoryExistence){
                    val classicScore = db.getScorePerCategory("Arcade")
                    val correctScore = classicScore.correctAns
                    val wrongScore = classicScore.wrongAns
                    val highScore = classicScore.highScore
                    val totalAnsweredQS = correctScore + wrongScore
                    val accuracy = (correctScore.toDouble() / totalAnsweredQS) * 100

                    _arcadeCorrectScore.postValue(correctScore)
                    _arcadeWrongScore.postValue(wrongScore)
                    _arcadeHighScore.postValue(highScore)
                    _arcadeTotalAnsweredQS.postValue(totalAnsweredQS)
                    _arcadeAccuracy.postValue(accuracy)
                }
            }
        }
    }

    fun getAllScores(){
        uiScope.launch {
            withContext(Dispatchers.IO) {

                _allScores.postValue(db.getAllScore(0))
            }
        }
    }

    fun permissions(it:Boolean){
        if(it){
            getClassicScore()
        }
    }
}