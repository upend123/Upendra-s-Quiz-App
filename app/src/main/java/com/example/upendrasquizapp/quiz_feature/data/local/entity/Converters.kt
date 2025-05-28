package com.example.upendrasquizapp.quiz_feature.data.local.entity

import androidx.room.TypeConverter
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.QuizState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Converters {

    // Convert List<QuizState> to JSON String
    @TypeConverter
    fun formQuizStateList(quizStateList: List<QuizState>): String {
        return Gson().toJson(quizStateList)
    }

    // Convert JSON String back to List<QuizState>
    @TypeConverter
    fun toQuizStateList(jsonString: String?): List<QuizState> {
        return if (jsonString.isNullOrEmpty()) {
            emptyList()
        } else {
            val quizStateListType = object : TypeToken<List<QuizState>>() {}.type
            Gson().fromJson(jsonString, quizStateListType) ?: emptyList()
        }
    }

    // Convert Long (timestamp) to formatted Date String
    @TypeConverter
    fun toDate(millis: Long): String {
        val format = SimpleDateFormat("dd/MM/yyyy hh:mma", Locale.getDefault())
        return format.format(Date(millis))
    }

    // Convert formatted Date String back to Long
    @TypeConverter
    fun fromDate(dateString: String?): Long {
        return if (dateString.isNullOrEmpty()) {
            System.currentTimeMillis()  // Default to current time if null or empty
        } else {
            try {
                val format = SimpleDateFormat("dd/MM/yyyy hh:mma", Locale.getDefault())
                format.parse(dateString)?.time ?: System.currentTimeMillis()
            } catch (e: Exception) {
                System.currentTimeMillis()  // Default if parsing fails
            }
        }
    }
}
