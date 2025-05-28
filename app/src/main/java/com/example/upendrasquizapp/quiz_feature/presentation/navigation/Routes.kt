package com.example.upendrasquizapp.quiz_feature.presentation.navigation

import java.net.URLEncoder

//QUIZ SCREEN CONSTANTS
const val ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val ARG_KEY_QUIZ_DIFFICULTY = "ak_quiz_difficulty"
const val ARG_KEY_QUIZ_TYPE = "ak_quiz_type"
//SCORE SCREEN CONSTANTS
const val ARG_KEY_QUIZ_SCORE = "ak_quiz_score"
const val ARG_KEY_QUIZ_NUMBER_OF_QUESTIONS = "ak_quiz_number_of_questions"

sealed class Routes(val route : String) {

    object HomeScreen: Routes(route = "home_Screen")

//    data class QuizScreen(val noOfQuizzes: Int,
//                          val category: String,
//                          val difficulty: String,
//                          val type : String):
//        Routes(route = "quiz_screen/{noOfQuizzes}/{category}/{difficulty}/{type}"){
//
//        fun createRoute(): String{
//            return "quiz_screen/{${noOfQuizzes}}/{${category.encodeURL()}}/{${difficulty.encodeURL()}}/{${type.encodeURL()}}"
//        }
//        }



    object QuizScreen: Routes(route = "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"){
        fun passQuizParams(noOfQuizzes: Int,category: String,difficulty: String,type : String): String{
            return "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_NUMBER}"
                    , newValue = noOfQuizzes.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_CATEGORY}"
                    , newValue = category
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_DIFFICULTY}"
                    , newValue = difficulty
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_TYPE}"
                    , newValue =type
                )
        }
    }

    object ScoreScreen : Routes(route = "score_screen/{$ARG_KEY_QUIZ_SCORE}/{$ARG_KEY_QUIZ_NUMBER_OF_QUESTIONS}"){

        fun passScoreParams(score: Int,noOfQuestions: Int): String{
            return "score_screen/{$ARG_KEY_QUIZ_SCORE}/{$ARG_KEY_QUIZ_NUMBER_OF_QUESTIONS}"
                .replace(oldValue = "{$ARG_KEY_QUIZ_SCORE}",
                    newValue = score.toString())
                .replace(oldValue = "{$ARG_KEY_QUIZ_NUMBER_OF_QUESTIONS}",
                    newValue = noOfQuestions.toString())
        }
    }
    object ProfileScreen : Routes(route = "profile_screen")
}


fun String.encodeURL(): String = URLEncoder.encode(this,"UTF-8")