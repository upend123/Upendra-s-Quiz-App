package com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz

import com.example.upendrasquizapp.quiz_feature.common.NetworkResponse
import com.example.upendrasquizapp.quiz_feature.data.local.entity.QuizEntity
import com.example.upendrasquizapp.quiz_feature.data.local.db.QuizDatabaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class GetQuizzesByDBUseCase(
    private val quizDatabaseRepo: QuizDatabaseRepo
) {
    operator fun invoke(): Flow<NetworkResponse<List<QuizEntity>>> =
        quizDatabaseRepo.getAllQuizzes()               // Room का Flow<List<QuizEntity>>
            .map<List<QuizEntity>, NetworkResponse<List<QuizEntity>>> { list ->
                NetworkResponse.Success(list)
            }
            .onStart { emit(NetworkResponse.Loading) } // flow शुरू होने पर एक बार Loading
            .catch { e -> emit(NetworkResponse.Error(massage = e.message ?: "Unknown error")) }
            .flowOn(Dispatchers.IO)
}