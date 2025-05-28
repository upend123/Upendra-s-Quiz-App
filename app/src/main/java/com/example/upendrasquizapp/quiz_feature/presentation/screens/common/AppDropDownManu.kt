package com.example.upendrasquizapp.quiz_feature.presentation.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.quiz_feature.presentation.utils.Dimens


@Preview
@Composable
fun Prev(){
    val list = listOf( "Item 1","Item 2")
    AppDropDownManu(menuName = "Drop Down",menuList = list, text = "Item 1", onDropDownClick = {})
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropDownManu(
    menuName: String,
    menuList : List<String>,
    text: String,
    onDropDownClick: (String) -> Unit
){
var isExpanded by remember {
    mutableStateOf(false)
}

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = Dimens.mediumPadding)) {
        Text(
                text = menuName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.black)
        )
        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            }

        ) {
           TextField(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = text,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                 ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded )
                },

colors = TextFieldDefaults.colors(unfocusedContainerColor = colorResource(R.color.purple_700),
    focusedContainerColor = colorResource(R.color.purple_700), focusedTrailingIconColor =colorResource(R.color.white), unfocusedTrailingIconColor = colorResource(R.color.white), unfocusedTextColor = colorResource(R.color.white), focusedTextColor = colorResource(R.color.white),
    ) ,

            )
            DropdownMenu(
                modifier = Modifier.background(
                    colorResource(R.color.white)),

                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded  = false
                }
            ) {
                menuList.onEachIndexed { index: Int,text: String ->

                    DropdownMenuItem(
                        onClick = {
                            onDropDownClick(menuList[index])
                            isExpanded = false
                        },
                        text = {
                            Text(
                                text = text,
                                color = colorResource(R.color.black)
                            )
                        }
, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )

                }
            }

        }

    }
}