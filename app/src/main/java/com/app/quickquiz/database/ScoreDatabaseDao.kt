package com.app.quickquiz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoreDatabaseDao {

    @Insert
    fun insert(category: CategoriesScore)

    @Update
    fun update(category: CategoriesScore)

    @Query("SELECT * from score_table WHERE id = :key")
    fun get(key: Long): CategoriesScore

    @Query("SELECT * FROM score_table WHERE category_name = :categoryName")
    fun getRightScore(categoryName: String): CategoriesScore

    @Query("SELECT EXISTS (SELECT 1 FROM score_table WHERE category_name = :categoryName)")
    fun exists(categoryName: String): Boolean?

    @Query("UPDATE score_table SET correct_ans = :correctAns,wrong_ans = :wrongAns,high_score = :highScore, indexNo =:indexNo, oldIndex =:oldIndexNo WHERE category_name = :categoryName")
    fun updateScore(categoryName: String, correctAns: Long, wrongAns: Long, highScore: Long, indexNo:Int,oldIndexNo:Int)

    @Query("SELECT SUM(correct_ans) FROM score_table ORDER BY id DESC")
    fun allCorrectScoreSum(): Long

    @Query("SELECT SUM(wrong_ans) FROM score_table ORDER BY id DESC")
    fun allWrongScoreSum(): Long

    @Query("SELECT * FROM score_table WHERE category_name = :categoryName")
    fun getScorePerCategory(categoryName: String): CategoriesScore

    @Query("SELECT category_name FROM score_table ORDER BY id DESC")
    fun allCategoryName(): List<String>

    @Query("SELECT * from score_table WHERE category_image != :catImg ORDER BY id DESC")
    fun getAllScore(catImg:Int): List<CategoriesScore>

}