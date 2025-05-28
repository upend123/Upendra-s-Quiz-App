package com.example.upendrasquizapp.quiz_feature.presentation.screens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.upendrasquizapp.R


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview(){
    QuizAppBar(quizAppBarTitle = "GK"){

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizAppBar(
    quizAppBarTitle: String,
    onBackPress: () -> Unit
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(R.color.purple_700),
            navigationIconContentColor = colorResource(R.color.white)
        ),
        title ={
            Text(
                text = quizAppBarTitle,
                color = colorResource(R.color.white),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        },
        navigationIcon = {
           IconButton(onClick = {onBackPress()}) {
               Icon(Icons.Default.ArrowBack, contentDescription = "BackPress Icon")
           }
        },

    )


}