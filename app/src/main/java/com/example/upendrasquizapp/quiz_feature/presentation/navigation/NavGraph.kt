package com.example.upendrasquizapp.quiz_feature.presentation.navigation

import android.app.Activity
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.upendrasquizapp.quiz_feature.data.local.db.QuizDatabaseViewModel
import com.example.upendrasquizapp.profile_feature.presentation.screens.profile.ProfileScreen
import com.example.upendrasquizapp.profile_feature.presentation.screens.profile.ProfileViewModel
import com.example.upendrasquizapp.quiz_feature.presentation.screens.home.HomeScreen
import com.example.upendrasquizapp.quiz_feature.presentation.screens.home.HomeScreenViewModel
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.QuizScreen
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.QuizViewModel
import com.example.upendrasquizapp.quiz_feature.presentation.screens.score.ScoreScreen
import kotlinx.coroutines.launch


@Composable
fun NavGraph(navController: NavHostController,onManuClick: () -> Unit) {

    val activity = (LocalActivity.current as ComponentActivity)
     val scope =  rememberCoroutineScope()
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(route = Routes.HomeScreen.route) {

            val viewModel: HomeScreenViewModel = hiltViewModel()
            val state = viewModel.homeState.collectAsState()
            val quizViewModel: QuizViewModel = hiltViewModel(activity)
            val quizDatabaseViewModel: QuizDatabaseViewModel = hiltViewModel(activity)
            val activity = LocalActivity.current as Activity
            HomeScreen(
                state = state.value,
                event = viewModel::onEvent,
                navController = navController,
                onMenuClick = { onManuClick() },
                onProfileClick = { navController.navigate(Routes.ProfileScreen.route) },
                onGenerateQuiz = {
                    scope.launch {
                        Log.d("ResetCallFromHome","Reset call from home screen")
                        quizViewModel.resetAll()
                        quizDatabaseViewModel.resetId()
                    }.invokeOnCompletion {
                        navController.navigate(
                            route = Routes.QuizScreen.passQuizParams(
                                noOfQuizzes = state.value.numberOfQuizzes,
                                category = state.value.category,
                                difficulty = state.value.difficulty,
                                type = state.value.type
                            )
                        )
                    }

                },
                activity
            )
        }
        composable(route = Routes.QuizScreen.route,
            arguments = listOf(
                navArgument(name = ARG_KEY_QUIZ_NUMBER){type = NavType.IntType},
                navArgument(name = ARG_KEY_QUIZ_CATEGORY) {type = NavType.StringType  },
                navArgument(name = ARG_KEY_QUIZ_DIFFICULTY) {type = NavType.StringType  },
                navArgument(name = ARG_KEY_QUIZ_TYPE) {type = NavType.StringType}
            )
        ) {
            val parentEntry = remember(it) { it }
            val viewModel = hiltViewModel<QuizViewModel>(activity)

            val state = viewModel.quizList.collectAsState()

            val noOfQuizzes  = it.arguments?.getInt(ARG_KEY_QUIZ_NUMBER) ?: 9
            val category = it.arguments?.getString(ARG_KEY_QUIZ_CATEGORY).orEmpty()
            val difficulty = it.arguments?.getString(ARG_KEY_QUIZ_DIFFICULTY).orEmpty()
            val type = it.arguments?.getString(ARG_KEY_QUIZ_TYPE).orEmpty()

            QuizScreen(numOfQuiz = noOfQuizzes, quizCategory = category, quizDifficulty = difficulty, quizType = type, onEvent = viewModel::onEvent ,state.value
               , viewModel = viewModel, onBackPress = {navController.popBackStack(Routes.HomeScreen.route,inclusive = false)},
                onSubmit = { score ->
                    Log.d("ScoreNavGraph: ",score.toString())
                    navController.navigate(route = Routes.ScoreScreen.passScoreParams(score =score, noOfQuestions = state.value.quizState.size))
                }
            )
        }
        composable(route = Routes.ScoreScreen.route,
            arguments = listOf(
                navArgument(name = ARG_KEY_QUIZ_SCORE) { type = NavType.IntType  }
                , navArgument(name = ARG_KEY_QUIZ_NUMBER_OF_QUESTIONS) { type = NavType.IntType }
            )) {
            val parentEntry = remember(it) { it }
            val viewModel = hiltViewModel<QuizViewModel>(activity)
            val quizDatabaseViewModel = hiltViewModel<QuizDatabaseViewModel>(activity)



            val noOfQuizzes = it.arguments?.getInt(ARG_KEY_QUIZ_NUMBER_OF_QUESTIONS)?:0
            val noOfCorrectAns = it.arguments?.getInt(ARG_KEY_QUIZ_SCORE)?:0

            Log.d("ScoreNoOfCorrectAns: ",noOfCorrectAns.toString())
            Log.d("ScoreForState",noOfCorrectAns.toString())

            ScoreScreen(noOfQuizzes = noOfQuizzes,noOfCorrectAns = noOfCorrectAns , viewModel = viewModel, quizDatabaseViewModel = quizDatabaseViewModel,

                onBackCall = {

scope.launch{
                        viewModel.resetSavedFlag()
                        Log.d("ResetCallFromScore", "Reset call from scorescreen ")
                        quizDatabaseViewModel.resetId()
                        viewModel.resetAll()
                         viewModel.resetFlagIsApiCall()
                    }.invokeOnCompletion {
    navController.popBackStack(Routes.HomeScreen.route,inclusive = false)}
}

            )
        }
composable(route = Routes.ProfileScreen.route){
    val viewModel : ProfileViewModel  = hiltViewModel<ProfileViewModel>()
    ProfileScreen(viewModel,onSaveProfile = viewModel::updateProfile , onBackCall = {navController.navigate(Routes.HomeScreen.route)})
}
    }
}

//    composable<Routes.QuizScreen> {
//        backStackEntry ->
//        val args = backStackEntry.toRoute<Routes.QuizScreen>()
//
//        val viewModel : QuizViewModel = hiltViewModel()
//       val state = viewModel.quizList.collectAsState()
//
// QuizScreen(
//     numOfQuiz = args.noOfQuizzes,
//     quizCategory = args.category,
//     quizDifficulty = args.difficulty,
//     quizType = args.type,
//     onBackPress = {navController.navigateUp()},
//     onEvent =  viewModel::onEvent ,
//     state = state.value,
// )
//}

