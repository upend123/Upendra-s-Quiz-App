package com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.quiz_feature.presentation.screens.common.noRippleClickable

@Preview
@Composable
fun PrevQuizOption() {
    QuizOption(
        optionNumber = "A",
        option = "Doreamon df shsfhfg hhdfgh tfds hsdfgdfgggdfgsd dfg sgdfsdgs dgf sdfg ssdf ag dhfdgdgdd",
        selected = false,
        onOptionClick = {},
        onUnselectedOption = {})

}

@Composable
fun QuizOption(
    optionNumber: String,
    option: String,
    selected: Boolean,
    onOptionClick: () -> Unit,
    onUnselectedOption: () -> Unit

)
{

    val optionTextColor =
        if (selected) colorResource(R.color.white) else colorResource(R.color.black)
    val backgroundColor =
        if (selected) colorResource(R.color.teal_200) else colorResource(R.color.white)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onOptionClick() },
        color = backgroundColor,
        shadowElevation = if (!selected) 4.dp else 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
        ) {
            if (!selected) {
                // Option indicator circle
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(if (selected) colorResource(R.color.white) else colorResource(R.color.teal_200))
                        .noRippleClickable(onClick = { onUnselectedOption() }),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = optionNumber,
                        color = colorResource(R.color.white),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,


                        )

                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            // Option text
            Text(
                text = option,
                color = optionTextColor,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                softWrap = true
            )

            Spacer(modifier = Modifier.width(12.dp))
            // Option indicator circle
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(if (selected) colorResource(R.color.white) else colorResource(R.color.teal_200))
                        .noRippleClickable(onClick = { onUnselectedOption() }),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        painter = painterResource(R.drawable.baseline_close_24),
                        contentDescription = "Deselect",
                        tint = colorResource(R.color.teal_200),
                        modifier = Modifier.size(18.dp)
                    )

                }
            }
        }
    }
}


