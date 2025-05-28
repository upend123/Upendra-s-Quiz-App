package com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.QuizState
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens

//@Preview
//@Composable
//fun PrevQuizInterface(){
//    QuizInterface(onOptionSelected = {},qNumber = 1, state = QuizState())
//}

@Composable
fun QuizInterface(
    onOptionSelected : (Int) -> Unit,
    qNumber: Int,
    state: QuizState
){

    val question = state.quiz?.question!!.replace(oldValue = "&quot;", newValue = "\"").replace(oldValue = "&#039;", newValue = "\'")
 Box(
     modifier = Modifier
         .wrapContentWidth()
         .wrapContentHeight()
         .background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp))
         .padding(24.dp),
     contentAlignment = Alignment.Center
 ){
     Column(modifier = Modifier.wrapContentHeight()){
         Row(modifier = Modifier.fillMaxWidth()) {
             Text(
                 modifier = Modifier.weight(1f),
                 text = "$qNumber",
                 color = colorResource(R.color.black),
                 fontSize = Dimens.SmallTextSize
             )
             Text(
                 modifier = Modifier.weight(9f),
                 text = question,
                 color = colorResource(R.color.black),
                 fontSize = Dimens.SmallTextSize
             )

         }
         Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

         Column(modifier = Modifier.padding(horizontal= 15.dp)) {
          val options = state.shuffledOptions.mapIndexed { index,rawQuestion ->
              val letter = (65 + index).toChar().toString()
              val cleanedQuestion = rawQuestion.replace(oldValue = "&quot;", newValue = "\"").replace(oldValue = "&#039;", newValue = "\'").replace(oldValue = "&deg;", newValue = "°").replace(oldValue = "&", newValue = "").replace(oldValue = ";", newValue = "").replace(oldValue = "&shy;", newValue = "-")
              letter to cleanedQuestion
          }
             Column {
                 options.forEachIndexed {
                     Index, (optionNumber,optionText) ->

                     if(optionText.isNotEmpty()){
                         QuizOption(
                             optionNumber = optionNumber,
                             option = optionText,
                             onOptionClick = { onOptionSelected(Index)},
                             selected = state.selectedOptions == Index ,
                             onUnselectedOption = {onOptionSelected(-1)}
                         )
                     }
                     Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                 }
             }
         }

     }
 }

}
