package com.app.quickquiz.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.quickquiz.R
import com.app.quickquiz.database.CategoriesScore
import com.app.quickquiz.database.ScoreDatabaseDao
import kotlinx.coroutines.*

class HomeViewModel(
    private val db: ScoreDatabaseDao,
) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToQuickQuiz = MutableLiveData<Boolean?>()
    val navigateToQuickQuiz: LiveData<Boolean?>
        get() = _navigateToQuickQuiz

    fun setInitialScore() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                val cateNameFile =
                    listOf("Classic","Arcade","Ordinary", "Geography",
                        "History", "Science",
                        "Sports", "Universe")

                for (fileName in cateNameFile) {
                    val existsOrNot = db.exists(fileName)!!
                    if (!existsOrNot) {
                        when (fileName) {
                            "Classic" -> {
                                val initialSore =
                                    CategoriesScore(0,
                                        fileName,
                                        R.drawable.ic_notifications,
                                        0,
                                        0,
                                        0,
                                        0)
                                insert(initialSore)
                            }
                            "Arcade" -> {
                                val initialSore =
                                    CategoriesScore(0,
                                        fileName,
                                        R.drawable.ic_notifications,
                                        0,
                                        0,
                                        0,
                                        0)
                                insert(initialSore)
                            }
                            "Ordinary" -> {
                                val initialSore =
                                    CategoriesScore(0,
                                        fileName,
                                        R.drawable.ic_rules,
                                        0,
                                        0,
                                        0,
                                        0)
                                insert(initialSore)
                            }
                            "Geography" -> {
                                val initialSore =
                                    CategoriesScore(0,
                                        fileName,
                                        R.drawable.ic_heart,
                                        0,
                                        0,
                                        0,
                                        0)
                                insert(initialSore)
                            }
                            "History" -> {
                                val initialSore =
                                    CategoriesScore(0,
                                        fileName,
                                        R.drawable.ic_history,
                                        0,
                                        0,
                                        0,
                                        0)
                                insert(initialSore)
                            }
                            "Science" -> {
                                val initialSore =
                                    CategoriesScore(0,
                                        fileName,
                                        R.drawable.ic_chemistry_category,
                                        0,
                                        0,
                                        0,
                                        0)
                                insert(initialSore)
                            }
                            "Sports" -> {
                                val initialSore =
                                    CategoriesScore(0,
                                        fileName,
                                        R.drawable.ic_sports,
                                        0,
                                        0,
                                        0,
                                        0)
                                insert(initialSore)
                            }
                            "Universe" -> {
                                val initialSore =
                                    CategoriesScore(0,
                                        fileName,
                                        R.drawable.ic_bookmark,
                                        0,
                                        0,
                                        0,
                                        0)
                                insert(initialSore)
                            }
                        }

                    }
                }
            }

        }
    }

    private fun insert(score: CategoriesScore) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(score)
            }
        }
    }

    fun doneQuickPlayNavigation() {
        _navigateToQuickQuiz.value = null
    }

    fun onStartQuiz() {
        _navigateToQuickQuiz.value = true
    }

    // navigate to time trial
    private val _navigateToQuizFilter = MutableLiveData<Boolean?>()
    val navigateToQuizFilter: LiveData<Boolean?>
        get() = _navigateToQuizFilter

    fun doneQuizFilterNavigation() {
        _navigateToQuizFilter.value = null
    }

    fun onNavigateToQuizFilter() {
        _navigateToQuizFilter.value = true
    }

    // navigate to category
    private val _navigateToCategory = MutableLiveData<Boolean?>()
    val navigateToCategory: LiveData<Boolean?>
        get() = _navigateToCategory

    fun doneCategoryNavigation() {
        _navigateToCategory.value = null
    }

    fun onNavigateToCategory() {
        _navigateToCategory.value = true
    }
}