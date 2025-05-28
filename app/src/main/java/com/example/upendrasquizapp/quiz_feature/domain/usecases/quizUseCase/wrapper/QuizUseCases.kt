package com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.wrapper

import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.DeleteQuizBYIdUseCase
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.DeleteQuizUseCase
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.GetQuizzesByDBUseCase
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.GetQuizzesByNetworkUseCase
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.UpsertQuizUseCase


data class QuizUseCases(
   val getQuizzesByNetworkUseCase: GetQuizzesByNetworkUseCase,
   val getQuizzesByDBUseCase : GetQuizzesByDBUseCase,
   val upsertQuizUseCase: UpsertQuizUseCase,
   val deleteQuizUseCase: DeleteQuizUseCase,
   val deleteQuizByIdUseCase: DeleteQuizBYIdUseCase
)