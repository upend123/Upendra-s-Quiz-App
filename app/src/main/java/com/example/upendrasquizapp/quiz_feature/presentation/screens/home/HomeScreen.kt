package com.example.upendrasquizapp.quiz_feature.presentation.screens.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.upendrasquizapp.quiz_feature.presentation.screens.common.AppDropDownManu
import com.example.upendrasquizapp.quiz_feature.presentation.screens.common.ButtonBox
import com.example.upendrasquizapp.quiz_feature.presentation.screens.home.component.HomeHeader
import com.example.upendrasquizapp.quiz_feature.presentation.navigation.Routes
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Constants
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun Prev(){
//    HomeScreen(state = StateHomeScreen(), event = {}, navController = NavController())
//}

@Composable
fun HomeScreen(
    state : StateHomeScreen,
    event : (EventsHomeScreen) -> Unit,
    navController : NavController,
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit,
    onGenerateQuiz : () -> Unit,
    activity : Activity
){

    BackHandler {
        activity.finish()
    }

    Column(modifier = Modifier.fillMaxSize()
        .verticalScroll(rememberScrollState())){

    HomeHeader(onManuClick = {onMenuClick()}, onProfileClick = {onProfileClick()})

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        AppDropDownManu(menuName = "Number of Questions", menuList = Constants.numberAsString, text = state.numberOfQuizzes.toString(), onDropDownClick = {
         event(EventsHomeScreen.SetNumberOfQuizzes(it.toInt()))
        })
        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
        AppDropDownManu(menuName = "Select Category",menuList = Constants.categories, text = state.category, onDropDownClick = {
            event(EventsHomeScreen.SetCategory(category = it))
        })
        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
        AppDropDownManu(menuName = "Select Difficulty",menuList = Constants.difficulty, text = state.difficulty, onDropDownClick = {
            event(EventsHomeScreen.SetDifficulty(difficulty = it))
        })
    Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
        AppDropDownManu(menuName = "Select Type",menuList = Constants.type, text = state.type, onDropDownClick = {
            event(EventsHomeScreen.SetType(type = it))
        })
    Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        ButtonBox(modifier = Modifier.fillMaxWidth(), text = "Generate Quiz", padding = Dimens.mediumPadding, onButtonClick = {

            onGenerateQuiz()
        })

    }


}




//navController.navigate(route = Routes.QuizScreen(noOfQuizzes = state.numberOfQuizzes, category = state.category, difficulty = state.difficulty, type = state.type).createRoute())