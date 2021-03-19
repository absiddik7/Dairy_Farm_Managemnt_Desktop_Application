 package com.app.quickquiz.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class CategoriesScore(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "category_name")
    val categoryName: String,

    @ColumnInfo(name = "category_image")
    val categoryImage: Int,

    @ColumnInfo(name = "correct_ans")
    val correctAns: Long,

    @ColumnInfo(name = "wrong_ans")
    val wrongAns: Long,

    @ColumnInfo(name = "high_score")
    val highScore: Long,

    )
