package com.example.upendrasquizapp.quiz_feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.QuizState


@Entity(tableName = "quiz_entity")
@TypeConverters(Converters::class)
data class QuizEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val quizStateList: List<QuizState> = emptyList(),

    val score: Int = 0,

    val createdAt: Long = System.currentTimeMillis()  // Default to current time
)