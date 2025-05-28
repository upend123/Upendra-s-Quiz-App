package com.example.upendrasquizapp.quiz_feature.presentation.screens.score

    import android.icu.text.DecimalFormat
    import android.util.Log
    import android.widget.Toast
    import androidx.activity.compose.BackHandler
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.heightIn
    import androidx.compose.foundation.layout.navigationBarsPadding
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.layout.wrapContentHeight
    import androidx.compose.foundation.layout.wrapContentWidth
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.res.colorResource
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.SpanStyle
    import androidx.compose.ui.text.buildAnnotatedString
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.text.withStyle
    import androidx.compose.ui.unit.dp
    import com.example.upendrasquizapp.R
    import com.example.upendrasquizapp.profile_feature.common.ShareOnAppIconButton
    import com.example.upendrasquizapp.profile_feature.common.TelegramShareButton
    import com.example.upendrasquizapp.quiz_feature.data.local.db.QuizDatabaseViewModel
    import com.example.upendrasquizapp.quiz_feature.data.local.entity.QuizEntity
    import com.example.upendrasquizapp.quiz_feature.presentation.screens.common.ButtonBox
    import com.example.upendrasquizapp.quiz_feature.presentation.screens.common.VibrateOnClick
    import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.QuizViewModel
    import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component.CongratulationsAnimation
    import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens

//
//    @Preview(showBackground = true, showSystemUi = true)
//    @Composable
//    fun PrevScoreScreen(){
//        ScoreScreen(10, 6, onBackCall = {}, quizVm = viewModel)
//    }

    @Composable
    fun ScoreScreen(
        noOfQuizzes: Int,
        noOfCorrectAns: Int,
        onBackCall: () -> Unit,
        quizDatabaseViewModel: QuizDatabaseViewModel ,
        viewModel: QuizViewModel
    ){
    var stateQuizDatabase =   quizDatabaseViewModel.stateStoredQuiz.collectAsState()
        var stateQuizList = viewModel.quizList.collectAsState()
val context = LocalContext.current
        var stateOfScore by remember { mutableStateOf(false) }
        BackHandler(){

            onBackCall()
        }

    Column(modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(
            modifier = Modifier
                .fillMaxWidth().weight(1f)
                .padding(horizontal = Dimens.mediumPadding),
            verticalArrangement = Arrangement.Center
        )
        {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    onBackCall()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_close_24),
                        contentDescription = "close",
                        tint = colorResource(id = R.color.black),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            // Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                    .background(Color(0xFFF0F0F0)),
                contentAlignment = Alignment.BottomCenter
            ) {

              Box(modifier = Modifier.wrapContentWidth().wrapContentHeight().align(Alignment.Center)){
                  CongratulationsAnimation()
              }


                Column(
                    modifier = Modifier.padding(
                        horizontal = Dimens.mediumPadding,
                        vertical = Dimens.mediumPadding
                    ), horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val annotatedString = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Black)) { append("You attempted ") }
                        withStyle(style = SpanStyle(color = Color.Blue)) {
                            append("$noOfQuizzes questions")
                        }
                        withStyle(style = SpanStyle(color = Color.Black)) { append(" and from that ") }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("$noOfCorrectAns answers")
                        }
                        withStyle(style = SpanStyle(color = Color.Black)) { append(" are correct ") }

                    }
                    val scorePercentage = calculatePercentage(noOfCorrectAns, noOfQuizzes)


                    Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                    Text(
                        text = "Congrats!",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = Dimens.MediumTextSize,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.heightIn(Dimens.MediumSpacerHeight))
                    Text(
                        text = if (!stateOfScore) "$scorePercentage Score%" else "0.0 Score%",
                        color = Color.Green,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = Dimens.LargeTextSize,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                    Text(
                        text = "Quiz completed successfully.",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = Dimens.SmallTextSize,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                    Text(
                        text = annotatedString,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = Dimens.SmallTextSize,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Share with us : ",
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = Dimens.SmallTextSize
                        )

                        val scoreMessage = buildString {
                            append("I just completed the “${stateQuizList.value.quizState.firstOrNull()?.quiz?.category}” quiz!\n")
                            append("Score: ${"%.2f".format(scorePercentage)}%\n")
                            append("Can you beat my score?")
                        }
                        ShareOnAppIconButton(
                            icon = painterResource(R.drawable.whatsapp_icon),
                            appPackage = "com.whatsapp",
                            message = scoreMessage
                        )

                        TelegramShareButton(
                            icon = painterResource(R.drawable.telegram_icon),
                            message = scoreMessage
                        )
                        //appPackage = "org.telegram.messenger"

                       ShareOnAppIconButton(
                           icon = painterResource(R.drawable.baseline_whatshot_24),
                           appPackage = "com.whatsapps",
                                   message = scoreMessage
                       )
                        Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))

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
            ButtonBox(
                modifier = Modifier.wrapContentWidth(),
                text = "Cancel",
                padding = Dimens.smallPadding,
                fraction = 0.45f,
                fontSize = Dimens.SmallTextSize
            ) {
                onBackCall()
            }
            Spacer(modifier = Modifier.weight(1f))

            ButtonBox(
                modifier = Modifier.wrapContentWidth(),
                text = "Save Quiz",
                padding = Dimens.smallPadding,
                borderColor = colorResource(R.color.purple_700),
                containerColor = colorResource(R.color.purple_700),
                fraction = 1f,
                textColor = colorResource(R.color.white),
                fontSize = Dimens.SmallTextSize
            ){

                Log.d("ScoreofCurrect", "Options: ${noOfCorrectAns}")
                Log.d("QuizState", "Options: ${stateQuizList.value.quizState}")

                val quizEntity = QuizEntity(
                    id = stateQuizDatabase.value.id,
                    score = noOfCorrectAns,
                    createdAt = System.currentTimeMillis(),
                    quizStateList = viewModel.getQuizStateList()
                )
                viewModel.upsertQuiz(quizEntity)
                  VibrateOnClick(context)
                Toast.makeText(context,"Quiz is Saved 😊",Toast.LENGTH_SHORT).show()

                onBackCall()
            }
        }

    }

    }

    private fun calculatePercentage(k: Int, n: Int): Double {
    if(k < 0 && n < 0) return 0.0
        var percentage = (k.toDouble() / n.toDouble()) * 100.0
        return DecimalFormat("0.00").format(percentage).toDouble()
    }