package com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz

import com.example.upendrasquizapp.quiz_feature.common.NetworkResponse
import com.example.upendrasquizapp.quiz_feature.domain.model.Quiz
import com.example.upendrasquizapp.quiz_feature.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetQuizzesByNetworkUseCase(
    private val quizRepository: QuizRepository
) {

 operator fun invoke(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String): Flow<NetworkResponse<List<Quiz>>> = flow {

     emit(NetworkResponse.Loading)

     delay(3000)
     try {
         emit(
             NetworkResponse.Success<List<Quiz>>(
                 data = quizRepository.geQuizzes(
                     amount = amount,
                     category = category,
                     difficulty = difficulty,
                     type = type
                 )
             )
         )
     } catch (e: Exception) {
         emit(NetworkResponse.Error(massage = e.message ?: "Unknown error"))
     }

 }.flowOn(Dispatchers.IO)
}