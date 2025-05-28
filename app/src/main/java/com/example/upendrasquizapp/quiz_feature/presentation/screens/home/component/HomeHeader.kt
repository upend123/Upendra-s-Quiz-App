package com.example.upendrasquizapp.quiz_feature.presentation.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PrevHomeHeader(){
    HomeHeader(onManuClick = {},onProfileClick = {})
}

@Composable
fun HomeHeader(
    onManuClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    Box(modifier = Modifier.fillMaxWidth()
        .height(Dimens.HomeTopBoxHeight)
        .background(
            color = colorResource(id = R.color.purple_700),
            shape = RoundedCornerShape(
                bottomEnd = Dimens.ExtraLargeCornerRadius,
                bottomStart = Dimens.ExtraLargeCornerRadius
            )
        ),
       ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.largePadding),
            horizontalArrangement = Arrangement.SpaceBetween
            , verticalAlignment = Alignment.CenterVertically
           ) {
           IconButton(
               onClick = onManuClick,
               interactionSource = remember { MutableInteractionSource() }
           ) {

               Icon(
                   painterResource(
                       id = R.drawable.baseline_menu_open_24),
                   contentDescription = "manu_icon",
                   modifier = Modifier
                       .weight(1f)
                       .size(Dimens.MediumIconSize),
                   tint = colorResource(id = R.color.white)

               )
           }
            Text(
                text = "Quiz App",
                color = colorResource(R.color.white)
                , style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(3.5f),
                textAlign = TextAlign.Center,
                fontSize = Dimens.LargeTextSize
            )
          IconButton(onClick = onProfileClick,
              interactionSource = remember { MutableInteractionSource() }) {
              Icon(
                  painterResource(
                      id = R.drawable.baseline_account_box_24),
                  contentDescription = " ",
                  modifier = Modifier
                      .weight(1f)
                      .size(Dimens.MediumIconSize),
                  tint = colorResource(id = R.color.white)
              )
          }


        }
    }

}
