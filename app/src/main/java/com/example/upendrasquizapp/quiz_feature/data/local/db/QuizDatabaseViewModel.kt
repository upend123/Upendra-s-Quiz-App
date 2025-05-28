package com.example.upendrasquizapp.quiz_feature.data.local.db


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upendrasquizapp.quiz_feature.common.NetworkResponse

import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.wrapper.QuizUseCases

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class QuizDatabaseViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases
): ViewModel() {

    // Database level state - private mutable state
        private val _stateStoredQuiz = MutableStateFlow(StateQuizDatabase())
    // Public immutable state
    val stateStoredQuiz = _stateStoredQuiz.asStateFlow()

    init {
        observeQuizUpdates()
    }
    fun deleteQuizById(id: Int){
        viewModelScope.launch {
            quizUseCases.deleteQuizByIdUseCase.invoke(id)  }

    }




    private fun observeQuizUpdates() {
        quizUseCases.getQuizzesByDBUseCase()
            .onEach { resp ->
                when (resp) {
                    is NetworkResponse.Loading ->
                        _stateStoredQuiz.update { it.copy(loading = true, onError = "") }

                    is NetworkResponse.Error ->
                        _stateStoredQuiz.update {
                            it.copy(
                                loading = false,
                                onError = resp.massage // Corrected spelling
                            )
                        }

                    is NetworkResponse.Success ->
                        _stateStoredQuiz.update {
                            it.copy(
                                loading = false,
                                dataOfQuizEntity = resp.data,
                                onError = ""
                            )
                        }
                }
            }
            .launchIn(viewModelScope)
    }


    fun  resetId(){
        _stateStoredQuiz.update {
            it.copy(
                id = 0,
            )
        }
    }



}