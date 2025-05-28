package com.example.upendrasquizapp.quiz_feature.data.mapper

import com.example.upendrasquizapp.quiz_feature.data.remote.dto.QuizDto
import com.example.upendrasquizapp.quiz_feature.domain.model.Quiz


fun QuizDto.toDomainQuiz(): Quiz{
    return Quiz(
        question = question,
        correctAnswer = correct_answer,
        incorrectAnswers = incorrect_answers,
        category = category,
        difficulty = difficulty,
        type = type
    )
}