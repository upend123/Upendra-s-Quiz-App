package com.example.upendrasquizapp.quiz_feature.presentation.screens.navigationDrawer

import ShimmerDrawerItem
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.quiz_feature.data.local.db.QuizDatabaseViewModel
import com.example.upendrasquizapp.quiz_feature.data.local.db.StateQuizDatabase
import com.example.upendrasquizapp.quiz_feature.data.local.entity.Converters
import com.example.upendrasquizapp.quiz_feature.presentation.navigation.NavGraph
import com.example.upendrasquizapp.quiz_feature.presentation.navigation.Routes
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.QuizState
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.QuizViewModel
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.StateQuizScreen
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component.ErrorScreen
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component.ShimmerEffectQuizScreen
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens
import kotlinx.coroutines.launch

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NavigationDrawer(navController: NavHostController,quizDatabaseViewModel: QuizDatabaseViewModel = hiltViewModel<QuizDatabaseViewModel>(),quizScreenViewModel: QuizViewModel = hiltViewModel<QuizViewModel>()){
var stateQuizDatabase = quizDatabaseViewModel.stateStoredQuiz.collectAsState()
var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  var isSearchExpanded by remember { mutableStateOf(false) }
var searchValue by remember { mutableStateOf("") }
var selectedQuizId by remember { mutableIntStateOf(-1) }
    var showAlert by remember { mutableStateOf(false) }
    var focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
val coroutineScope =  rememberCoroutineScope()

val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    LaunchedEffect(drawerState.isClosed) {
        if(drawerState.isClosed) focusManager.clearFocus()
    }




    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = currentRoute == Routes.HomeScreen.route,
        drawerContent = {

            ModalDrawerSheet(modifier = if(isSearchExpanded) Modifier.fillMaxWidth() else Modifier
                .width(280.dp)
              ) {

                if (showAlert) {
                    println("Showing alert dialog for quiz ID: $selectedQuizId")
                    AlertDialog(
                        onDismissRequest = { showAlert = false },
                        title = { Text(text = "Delete Quiz") },
                        text = { Text(text = "Are you sure you want to delete this quiz?") },
                        confirmButton = {
                            TextButton(onClick = {
                                coroutineScope.launch {
                                    quizDatabaseViewModel.deleteQuizById(selectedQuizId)
                                    showAlert = false
                                }
                            }) {
                                Text("Yes")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showAlert = false }) {
                                Text("No")
                            }
                        }
                    )
                }

                TextField(
                    value = searchValue,
                    onValueChange = {
                        searchValue = it
                    },
                    placeholder = {
                        Text("Search")
                    },
                    leadingIcon = {
                        IconButton(
                            onClick = { if(isSearchExpanded){
                                isSearchExpanded = false
                                focusManager.clearFocus()
                            }
                                else{
                                    isSearchExpanded = true
                                focusRequester.requestFocus()
                            }
                                      },
                        ){
                            Icon(imageVector =if(isSearchExpanded) Icons.Default.ArrowBack else Icons.Default.Search , contentDescription = "search" )
                        }
                    },
                    trailingIcon = {
                        if(isSearchExpanded){
                            IconButton(onClick = { searchValue = ""}) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = "clear_search")
                            }
                        }
                    },
                    shape = CircleShape,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            isSearchExpanded = focusState.isFocused
                        }

                )
                LazyColumn{
item {     DrawerItem(
    label = "QuizScreen",
    selected = currentRoute == Routes.HomeScreen.route,
    onClick = {  coroutineScope.launch {
        drawerState.close()
        navController.navigate(Routes.HomeScreen.route) {
            popUpTo(Routes.HomeScreen.route) { inclusive = true }
            launchSingleTop = true
        }}},
    icon = Icons.Default.Home,
    onLongClick = {}
)}
             item {

                 DrawerItem(
                     label = "Profile",
                     selected = currentRoute == Routes.ProfileScreen.route,
                     onClick = {  coroutineScope.launch {
                         drawerState.close()
                     }
                         navController.navigate(Routes.ProfileScreen.route){
                             launchSingleTop = true
                         }
                     },
                     icon = Icons.Default.Person,
                     onLongClick = {}
                 )
             }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(Dimens.verySmallViewHeight)
                                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                                .padding(4.dp)
                                .background(colorResource(R.color.black))
                        )
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                            contentAlignment = Alignment.TopStart){
                            Text(
                                text = "Past Quizzes",
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                    //stored items
                    item {
                        if(isQuizFatched(stateQuizDatabase.value)){

                            val quizEntityList= stateQuizDatabase.value.dataOfQuizEntity

                           val filteredList = if(searchValue.isBlank()) quizEntityList
                            else {
                                quizEntityList.filter { quiEntity ->

                                    val label = buildString {
                                        val quizState = quiEntity.quizStateList.firstOrNull()
                                        append(quizState?.quiz?.category ?: "")
                                        append(" | ")
                                        append(quizState?.quiz?.difficulty ?: "")
                                        append(" | ")
                                        append(quizState?.quiz?.type ?: "")
                                        append(" | ")
                                        append(Converters().toDate(quiEntity.createdAt))

                                    }
                                    label.contains(searchValue.trim(),ignoreCase = true)

                                }
                           }
                          filteredList.forEach { quizEntity ->
                                val quizState = quizEntity.quizStateList.firstOrNull()
                                val date: String = Converters().toDate(quizEntity.createdAt)
                                val score: String = quizEntity.score.toString()
                                val quiz = quizState?.quiz
                                val category = quiz?.category ?: "kya pta Category"
                                val type = quiz?.type ?: "kya pta Type"
                                val difficulty = quiz?.difficulty ?: "kya pta Difficulty"

                                DrawerItem(
                                    label = "$category | $difficulty | $type | $score | $date",
                                    selected = false,
                                    onClick = {
                                        coroutineScope.launch {
                                            quizScreenViewModel.changeFlagIsApiCall(false)

                                            stateQuizDatabase.value.id = quizEntity.id

                                            quizScreenViewModel.resetIsScoreUpdated()
                                            quizScreenViewModel.changeQuizState(quizEntity.quizStateList)
                                            navController.navigate(
                                                route = Routes.QuizScreen.passQuizParams(
                                                    noOfQuizzes = quizEntity.quizStateList.size,
                                                    category = category,
                                                    difficulty = difficulty,
                                                    type = type
                                                )
                                            )
                                            drawerState.close()
                                        }
                                    },
                                    onLongClick = {
                                        selectedQuizId = quizEntity.id
                                        println("Setting selectedQuizId to ${quizEntity.id}") // Debug log
                                        showAlert = true
                                    }
                                )
                            }
                        }

                    }

                }


            }


        }

    )
    {
        val insets  = WindowInsets.systemBars
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white))
            .padding(insets.asPaddingValues())
            , contentAlignment = Alignment.Center){
            NavGraph(
                navController = navController,
                onManuClick = { coroutineScope.launch { drawerState.open() } })
        }


    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrawerItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    icon: ImageVector? = ImageVector.vectorResource(R.drawable.baseline_quiz_24)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 4.dp, vertical = 4.dp)          // 전체 padding
            .background(
                color = if (selected) colorResource(R.color.purple_700) else Color.Transparent,
                shape = RoundedCornerShape(30.dp)
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)       // आइकन और टेक्स्ट के बीच space
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)                                     // आइकन का कॉन्सिस्टेंट साइज
                    .align(Alignment.CenterVertically),              // vertical center
                tint = if (selected) Color.White else colorResource(R.color.black)
            )
        }

        Text(
            text = label,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = if (selected) 18.sp else 16.sp,
            color = if (selected) Color.White else colorResource(R.color.black),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)   ,                      // टेक्स्ट को बाकी जगह ले लेने दें
        )
    }
}


//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun DrawerItem(
//    label: String,
//    selected: Boolean,
//    onClick: () -> Unit,
//    onLongClick: () -> Unit,
//    icon: ImageVector? = ImageVector.vectorResource(R.drawable.baseline_quiz_24)
//) {
//    NavigationDrawerItem(
//        label = {
//            Text(
//                text = label,
//                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
//                fontSize = if (selected) 18.sp else 16.sp,
//                color = if (selected) Color.White else colorResource(R.color.black),
//                overflow = TextOverflow.Ellipsis
//            )
//        },
//        selected = selected,
//        onClick = {},
//        icon = {
//            if (icon != null) {
//                Icon(
//                    imageVector = icon,
//                    contentDescription = label,
//                    tint = if (selected) Color.White else colorResource(R.color.black)
//                )
//            }
//        },
//        colors = NavigationDrawerItemDefaults.colors(
//            unselectedContainerColor = Color.Transparent,
//            selectedContainerColor = Color.Transparent,
//            selectedTextColor = colorResource(R.color.white),
//            unselectedTextColor = colorResource(R.color.black)
//        ),
//        modifier = Modifier
//            .padding(4.dp)
//            .fillMaxWidth()
//            .height(60.dp)
//            .background(
//                color = if (selected) colorResource(R.color.purple_700) else Color.Transparent,
//                shape = RoundedCornerShape(30.dp)
//            )
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onTap = { onClick() },
//                    onLongPress = {
//                        Log.d("DrawerItem", "Long click detected")
//                        onLongClick()
//                    }
//                )
//            }
//
//    )
//}
//

@Composable
fun isQuizFatched(state: StateQuizDatabase): Boolean {
    var bool: Boolean = false
    when{

        state.loading -> {
            repeat(12) {
                ShimmerDrawerItem()
            }

            bool = false
        }

        state.dataOfQuizEntity .isNotEmpty() -> {
            bool = true
        }

        state.onError.isNotEmpty() == true -> {
            DrawerItemErrorScreen()
            bool = false
        }

        else -> {
            repeat(12) {
                ShimmerDrawerItem()
            }
            bool = false
        }
    }

    return bool
}



/*
   .pointerInput(Unit) {
        detectTapGestures(
            onTap = { onClick() },
            onLongPress = {
                Log.d("DrawerItem", "Long click detected")
                onLongClick()
            }
        )
    }
 */