package com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens

@Preview
@Composable
fun ShimmerEffectQuizScreen(){



    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween

    ) {


        Column(modifier = Modifier.fillMaxWidth()){

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))
        // Simulated Top Info Row shimmer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )
        }

       Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        // Simulated Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .shimmerEffect()
        )
    }
        //Spacer(modifier = Modifier.height(50.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(12.dp))
                .shimmerEffect()
                .align(Alignment.CenterHorizontally)
        )

       // Spacer(modifier = Modifier.weight(1f))

        // Simulated Bottom Buttons
        Row(
            modifier = Modifier

                .fillMaxWidth()
                .padding(bottom = 20.dp, start = 12.dp, end = 12.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                    .shimmerEffect()
            )
        }
    }
}



fun Modifier.shimmerEffect() = composed{
val transition = rememberInfiniteTransition(label = "")
  val alpha = transition.animateFloat(
      initialValue = 0.2f,
      targetValue = 0.9f,
      animationSpec = infiniteRepeatable(
          animation = tween(durationMillis = 1000),
          repeatMode = RepeatMode.Reverse
      ),
      label = ""
  ).value
    background(color = Color.Gray.copy(alpha))
}