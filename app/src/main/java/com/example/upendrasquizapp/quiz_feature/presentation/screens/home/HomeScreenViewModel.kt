package com.example.upendrasquizapp.quiz_feature.presentation.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel  @Inject constructor(): ViewModel() {

    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState

    fun onEvent(event: EventsHomeScreen){
        when(event){

            is EventsHomeScreen.SetType -> {
                _homeState.value = homeState.value.copy(type = event.type)

            }
            is EventsHomeScreen.SetCategory ->{
                _homeState.value = homeState.value.copy(category = event.category)
            }
            is EventsHomeScreen.SetDifficulty -> {
                 _homeState.value = homeState.value.copy(difficulty = event.difficulty)
            }
            is EventsHomeScreen.SetNumberOfQuizzes -> {
                _homeState.value = homeState.value.copy(numberOfQuizzes = event.numberOfQuizzes)

            }
            else -> {
            }
        }

    }

}
