package com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.quiz_feature.presentation.screens.common.ButtonBox
import com.example.upendrasquizapp.quiz_feature.presentation.screens.common.QuizAppBar
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component.ErrorScreen
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component.QuizAlertDialog
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component.QuizInterface
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component.ShimmerEffectQuizScreen
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Constants
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens
import kotlinx.coroutines.launch

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun Prev() {
//    QuizScreen(
//        numOfQuiz = 12,
//        quizCategory = "GK",
//        quizDifficulty = "Hard",
//        quizType = "Multiple Choice",
//        onEvent = {},
//        state = StateQuizScreen(),
//        onBackPress = {},
//        onSubmit = {}
//    )
//}

@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    onEvent: (EventQuizScreen) -> Unit,
    state: StateQuizScreen,
    viewModel: QuizViewModel,
    onBackPress: () -> Unit,
    onSubmit: (Int) -> Unit,

    ) {

    val newState = viewModel.quizState.collectAsState()

    val scope = rememberCoroutineScope()
    var showAlert by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState { state.quizState.size }

        LaunchedEffect(Unit) {
            Log.d("LaunchedEffect", "Launched effect is called")
            if(viewModel.isApiCall.value) {
                val difficulty = when (quizDifficulty) {
                    "Hard" -> "hard"
                    "Medium" -> "medium"
                    else -> "easy"
                }
                val type = when (quizType) {
                    "TrueOrFalse" -> "boolean"
                    else -> "multiple"
                }
                val categoryId = Constants.categoriesMap[quizCategory] ?: 9
                Log.d("onEvent","On Event is called")
                onEvent(EventQuizScreen.GetQuizzesByApi(numOfQuiz, categoryId, difficulty, type))
            }
            viewModel.isScoreUpdated.collect {
                if(it){
                    Log.d("checkIt","onSubmit is called")
                    onSubmit(newState.value.score)
                    viewModel.resetIsScoreUpdated()
                }
            }

            // else
//            onEvent(EventQuizScreen.GetQuizzesByDB)
    }

    if ( !viewModel.isApiCall.value ||  isQuizFatched(state)) {


        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            // Top Fixed AppBar and Row
            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                QuizAppBar(quizCategory) { onBackPress() }

                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Questions: $numOfQuiz",
                        modifier = Modifier.padding(Dimens.smallPadding),
                        color = colorResource(R.color.black)
                    )
                    Text(
                        text = quizDifficulty,
                        modifier = Modifier.padding(Dimens.smallPadding),
                        color = colorResource(R.color.black)
                    )
                }

                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.verySmallViewHeight)
                        .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                        .background(colorResource(R.color.black))
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .navigationBarsPadding(), contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(Dimens.smallPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

//Horizontal pager
                        HorizontalPager(state = pagerState) { index ->

                            QuizInterface(
                                onOptionSelected = { indexOfSelectedOption ->
                                    onEvent(
                                        EventQuizScreen.SetOptionSelected(
                                            quizStateIndex = index,
                                            selectedOptionIndex = indexOfSelectedOption
                                        )
                                    )
                                },
                                qNumber = index + 1,
                                state.quizState[index]
                            )
                        }

                    }
                }

                val buttonsText by remember {
                    derivedStateOf {
                        when (pagerState.currentPage) {
                            0 -> {
                                listOf("", "Next")
                            }

                            (state.quizState.size - 1) -> {
                                listOf("Previous", "Submit")
                            }

                            else -> {
                                listOf("Previous", "Next")
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        //.align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .background(
                            color = colorResource(R.color.white),
                            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        )
                        .navigationBarsPadding(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (buttonsText[0].isNotEmpty()) {
                        ButtonBox(
                            modifier = Modifier.width(120.dp),
                            text = "Previous",
                            padding = Dimens.smallPadding,
                            fraction = 0.45f,
                            fontSize = Dimens.SmallTextSize
                        ) { /* Previous button action */
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }

                    }

                    Spacer(modifier = Modifier.weight(1f))

                    ButtonBox(
                        modifier = Modifier.width(120.dp),
                        text = buttonsText[1],
                        padding = Dimens.smallPadding,
                        borderColor = if (pagerState.currentPage == state.quizState.size - 1) colorResource(
                            R.color.teal_200
                        ) else colorResource(R.color.purple_500),
                        containerColor = if (pagerState.currentPage == state.quizState.size - 1) colorResource(
                            R.color.teal_200
                        ) else colorResource(R.color.purple_700),
                        fraction = 1f,
                        textColor = if (pagerState.currentPage == state.quizState.size - 1) colorResource(
                            R.color.black
                        ) else colorResource(R.color.white),
                        fontSize = Dimens.SmallTextSize
                    ) { /* Next button action */
                        if (pagerState.currentPage == state.quizState.size - 1) {
                            //navigate to score screen

                            if(state.quizState.any { it.selectedOptions == -1 })
                            {
                                showAlert = true
                            }else {
                                Log.d("CalculateFinalScore","calculate final score is call from quizscreen ")
                                onEvent(EventQuizScreen.CalculateFinalScore)
 }

                        } else {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                        }
                    }
                }


            }

        }
        if (showAlert){
            Box(modifier = Modifier.fillMaxSize()){
                QuizAlertDialog(
                    title = "Incomplete Quiz",
                    message = "Please attempt all questions before submitting.",
                    onDismiss = { showAlert = false },
                    onConfirm = { showAlert = false }
                )}

        }
    }
}

@Composable
fun isQuizFatched(state: StateQuizScreen): Boolean {
    var bool: Boolean = false
    when {

        state.loading -> {
            ShimmerEffectQuizScreen()
            bool = false
        }

        state.quizState.isNotEmpty() == true -> {
            bool = true
        }

        state.error.isNotEmpty() == true -> {
             ErrorScreen()
            bool = false
        }

        else -> {
            ShimmerEffectQuizScreen()
            bool = false
        }
    }

    return bool
}