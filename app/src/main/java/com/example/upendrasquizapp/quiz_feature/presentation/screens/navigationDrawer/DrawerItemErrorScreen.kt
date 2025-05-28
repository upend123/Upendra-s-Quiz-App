package com.example.upendrasquizapp.quiz_feature.presentation.screens.navigationDrawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens

@Composable
fun DrawerItemErrorScreen() {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(R.drawable.baseline_error_outline_24), contentDescription = "error", tint = colorResource(R.color.purple_700))
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
            Text(text = "No Data Found!", color = colorResource(R.color.purple_700), fontSize = Dimens.SmallTextSize, fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Serif, fontStyle = FontStyle.Normal
            )
        }
    }
