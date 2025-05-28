package com.example.upendrasquizapp.quiz_feature.presentation.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens
//@Preview
//@Composable
//fun prev(){
//    ButtonBox(
//        text = "Next",
//        padding = Dimens.smallPadding,
//        borderColor = colorResource(R.color.teal_200),
//        containerColor = colorResource(R.color.purple_700),
//        fraction = 1f,
//        textColor = colorResource(R.color.black),
//    ){}
//}

@Composable
fun ButtonBox(
    modifier: Modifier = Modifier,
    text : String,
    padding: Dp = Dimens.smallPadding,
    borderColor : Color = colorResource(R.color.black),
    containerColor: Color = colorResource(R.color.white),
    textColor: Color = colorResource(R.color.black),
    fraction: Float = 1f,
    fontSize : TextUnit = Dimens.MediumTextSize,
    onButtonClick : () -> Unit
){
    Box(modifier = modifier
        .padding(padding)
        .border(width = Dimens.verySmallViewHeight, color = borderColor, shape = RoundedCornerShape(Dimens.LargeCornerRadius))
        .clickable(onClick = {onButtonClick()})
        .fillMaxWidth(fraction)
        .height(Dimens.MediumBoxHeight)
        .background(containerColor, shape = RoundedCornerShape(Dimens.LargeCornerRadius))
        .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
        , contentAlignment = Alignment.Center,
        ){

        Text(text = text,
            fontSize = fontSize,
            color = textColor,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            )

    }
}