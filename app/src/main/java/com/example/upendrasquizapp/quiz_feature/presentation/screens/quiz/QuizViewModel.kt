package com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upendrasquizapp.quiz_feature.common.NetworkResponse
import com.example.upendrasquizapp.quiz_feature.data.local.entity.QuizEntity
import com.example.upendrasquizapp.quiz_feature.domain.model.Quiz

import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.wrapper.QuizUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class QuizViewModel @Inject constructor(
 private val quizUseCases: QuizUseCases
): ViewModel() {

    private val _quizList = MutableStateFlow(StateQuizScreen())
   private val _isScoreUpdated  = MutableSharedFlow<Boolean>(replay = 0)
    private val _isApiCall = mutableStateOf(true)

    val quizState = _quizList
    val isApiCall = _isApiCall
    val quizList = _quizList
    val isScoreUpdated = _isScoreUpdated.asSharedFlow()
    // Flag to check if data is already saved
    private var isSaved = false

    fun changeFlagIsApiCall(flag : Boolean){
        _isApiCall.value = flag
    }
    fun resetFlagIsApiCall(){
        _isApiCall.value = true
    }

    fun changeQuizState(state: List<QuizState>) {
        _quizList.value = StateQuizScreen(
            loading = false,
            error = "",
            score = 0,
            quizState = state.map {
            QuizState(quiz = it.quiz , shuffledOptions = it.shuffledOptions, selectedOptions = it.selectedOptions)
        })
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun resetIsScoreUpdated() {
        _isScoreUpdated.resetReplayCache()
        viewModelScope.launch {
            _isScoreUpdated.emit(false)
            Log.d("RESET","resetisScoreUpdate is called from quizViewModel")
        }
    }


    fun convertToQuizStateList(quizEntities: List<QuizEntity>): List<QuizState> {
        return quizEntities.map { entity ->
            val quiz = entity.quizStateList.firstOrNull()?.quiz ?: Quiz(
                category = "TODO",
                type = "TODO()",
                difficulty = "TODO()",
                question = "TODO()",
                correctAnswer = "TODO()",
                incorrectAnswers = listOf("TODO()","TODO",)
            )
            val shuffledOptions = quiz.incorrectAnswers.toMutableList().apply {
                add(quiz.correctAnswer)
                shuffle()
            }

            QuizState(
                quiz = quiz,
                shuffledOptions = shuffledOptions,
                selectedOptions = entity.quizStateList.firstOrNull()?.selectedOptions ?: -1
            )
        }
    }

    fun updateQuizListFromDB(quizEntities: List<QuizEntity>) {
        val quizStates = convertToQuizStateList(quizEntities)
        _quizList.value = StateQuizScreen(
            quizState = quizStates,
            loading = false,
            error = ""
        )
    }

     fun onEvent(event: EventQuizScreen){
           when(event){
               is EventQuizScreen.GetQuizzesByApi -> {
                   getQuizzesByApi(event.noOfQuizzes,event.category,event.difficulty,event.type)
               }
               is EventQuizScreen.GetQuizzesByDB -> {
                   getQuizzesByDB()
               }

               is EventQuizScreen.SetOptionSelected -> {
                   updateQuizStateList(quizStateIndex = event.quizStateIndex,selectedOptionIndex = event.selectedOptionIndex)
               }
               is EventQuizScreen.CalculateFinalScore -> {

                   viewModelScope.launch {

                   val score =  updatedScore()

                   _quizList.update {
                       it.copy(score = score)
                   }
                       Log.d("calculateScoreCalled","calculate score called true")

                           _isScoreUpdated.emit(true)
//                       _quizList.collectLatest {
//                           state ->
//                           if(state.score == score)
//                               _isScoreUpdated.emit(true)
//
//                           return@collectLatest
//                       }

                   }
               }
               else -> {

               }
           }
       }


    private fun updateQuizStateList(quizStateIndex: Int, selectedOptionIndex: Int) {
                //updating the selected option Index of quiz
        _quizList.update { currentState ->
            currentState.copy(
                quizState = currentState.quizState.mapIndexed { index, quizState ->
                    if (index == quizStateIndex)
                        quizState.copy(selectedOptions = selectedOptionIndex)
                    else
                        quizState
                }
            )
        }

    }

    private fun updatedScore(): Int{

            val totalScore = quizList.value.quizState.count { quizState ->

                val correctAns = quizState.quiz?.correctAnswer?.sanitizeAnswer()
                val selectedAns = quizState.selectedOptions?.takeIf {
                    it >= 0 && it < quizState.shuffledOptions.size
                }?.let {
                    quizState.shuffledOptions[it].sanitizeAnswer()
                }
                Log.d("check:", "CorrectAns: $correctAns -> SelectedAns: $selectedAns")
                correctAns == selectedAns

            }
            _quizList.update { currentState ->
                currentState.copy(score = totalScore)
            }
            Log.d("Updated Score: ", _quizList.value.score.toString())
        return totalScore
        }

    private fun String.sanitizeAnswer(): String {
        return this.replace(oldValue = "&quot;", newValue = "\"")
            .replace(oldValue = "&#039;", newValue = "\'")
            .replace(oldValue = "&deg;", newValue = "°")
            .replace(oldValue = "&", newValue = "")
            .replace(oldValue = ";", newValue = "")
            .replace(oldValue = "&shy;", newValue = "-")
    }




    private fun getQuizzesByApi(amount : Int,category: Int,difficulty: String,type: String){
viewModelScope.launch {
   quizUseCases.getQuizzesByNetworkUseCase.invoke(amount,category,difficulty,type).collect {
        networkResponse ->
        when(networkResponse){
            is NetworkResponse.Loading -> {
             _quizList.value = StateQuizScreen(loading = true)
            }
            is NetworkResponse.Error -> {
                _quizList.value = StateQuizScreen(error = networkResponse.massage)
            }

            is NetworkResponse.Success -> {
            val listQuizState : List<QuizState> = getListOfQuizState(networkResponse.data)
                _quizList.value = StateQuizScreen(quizState = listQuizState)
            }

        }
    }
}
    }
    fun getQuizzesByDB(){

    }

    fun upsertQuiz(quizEntity: QuizEntity) {
        if(isSaved) return // Prevent multiple saves
        viewModelScope.launch {

            quizUseCases.upsertQuizUseCase(quizEntity)
            isSaved = true
            // Only reset necessary fields

        }
    }

    fun resetSavedFlag(){
        isSaved = false
    }

    fun getQuizStateList() : List<QuizState>{

        val quizStateList = mutableListOf<QuizState>()
        for (quizState in _quizList.value.quizState){

            val state = QuizState(
                quiz = Quiz(
                    category = quizState.quiz?.category ?: "UNKNOWN",
                    type = quizState.quiz?.type ?: "UNKNOWN",
                    difficulty = quizState.quiz?.difficulty ?: "UNKNOWN",
                    question = quizState.quiz?.question ?: "UNKNOWN",
                    correctAnswer = quizState.quiz?.correctAnswer ?: "UNKNOWN",
                    incorrectAnswers = quizState.quiz?.incorrectAnswers ?: listOf("KNow, Know ,know")
                ),
                shuffledOptions = quizState.shuffledOptions,
                selectedOptions = quizState.selectedOptions
            )
           quizStateList.add(state)
        }
return quizStateList
    }

    fun delete(quizEntity: QuizEntity) {
        viewModelScope.launch {
            quizUseCases.deleteQuizUseCase(quizEntity)
            // No need to reset state here as Flow will auto-update
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun resetAll() {
        // QuizViewModel.kt में
            _quizList.value = StateQuizScreen() // सभी quiz डेटा और स्कोर reset
            _isScoreUpdated.resetReplayCache() // पुराने emissions clear
            viewModelScope.launch {
                _isScoreUpdated.emit(false) // निश्चित रूप से false सेट करें
            }

    }



}



fun getListOfQuizState(list: List<Quiz>): List<QuizState> {

    val listOfQuizState = mutableListOf<QuizState>()

    for (quiz in list) {

        val shuffledList = mutableListOf<String>().apply {
            add(quiz.correctAnswer)
            addAll(quiz.incorrectAnswers)
            shuffle()
        }
   listOfQuizState.add(QuizState(quiz = quiz, shuffledOptions = shuffledList,-1))
    }
       return listOfQuizState

}




