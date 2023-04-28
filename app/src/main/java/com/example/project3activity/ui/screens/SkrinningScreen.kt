package com.example.project3activity.ui.screens


import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.project3activity.DaftarLayananActivity
import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.zIndex
import com.example.project3activity.R
import com.example.project3activity.SkrinningActivity
import com.example.project3activity.SkrinningProccessActivity
import com.example.project3activity.ui.screens.Question as Question1

@Composable
fun SkrinningScreen(userId : String) {

    val choices = listOf("Ya", "Tidak")
    val selectedStates = remember { mutableStateListOf("", "", "", "", "") }
    val lContext = LocalContext.current

    Box(modifier = Modifier.zIndex(3f)) {
        //Pattern
        Image(
            painter = painterResource(id = R.drawable.pattern),
            contentDescription = "pattern",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(width = 400.dp)
                .height(height = 85.dp)
        )
    }

    val button_xl = Modifier
        .size(width = 314.dp, height = 120.dp)
        .shadow(4.dp, shape = RoundedCornerShape(20.dp))
        .defaultMinSize(1.dp, minHeight = 1.dp)

    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp),
        ) {
            Text(
                text = stringResource(id = com.example.project3activity.R.string.Skrinning_menu),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                ),
            )
        }


        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize()
                .fillMaxHeight()
        )
        {

            /*tombol kembali*/
            TextButton(
                onClick = {
                    lContext.startActivity(
                        Intent(lContext, DaftarLayananActivity::class.java)
                            .putExtra("userId", userId)
                    )
                },
                modifier = Modifier.padding(start = 20.dp)

            ) {
                Image(
                    painter = painterResource(id = com.example.project3activity.R.drawable.other_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)

                )
            }


            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxSize()
                    .fillMaxHeight()
            ) {


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

//                Konten

                    Column(
                        modifier = Modifier
                            .shadow(
                                elevation = 2.dp,
                                shape = RoundedCornerShape(20.dp)
                            )
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(550.dp)
                                    .width(314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                                    .padding(
                                        start = 15.dp,
                                        top = 15.dp,
                                        bottom = 15.dp,
                                        end = 15.dp
                                    )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                ) {
                                    Column {
                                        Question1(
                                            stringResource(id = R.string.Skrinning_1),
                                            selectedStates[0],
                                            choices
                                        ) {
                                            selectedStates[0] = it
                                        }

                                        Question1(
                                            stringResource(id = R.string.Skrinning_2),
                                            selectedStates[1],
                                            choices
                                        ) {
                                            selectedStates[1] = it
                                        }

                                        Question1(
                                            stringResource(id = R.string.Skrinning_3),
                                            selectedStates[2],
                                            choices
                                        ) {
                                            selectedStates[2] = it
                                        }

                                        Question1(
                                            stringResource(id = R.string.Skrinning_4),
                                            selectedStates[3],
                                            choices
                                        ) {
                                            selectedStates[3] = it
                                        }

                                        Question1(
                                            stringResource(id = R.string.Skrinning_5),
                                            selectedStates[4],
                                            choices
                                        ) {
                                            selectedStates[4] = it
                                        }
                                    }
                                }

                            }
                        }
                    }

                    Column(
                        modifier = Modifier.padding(top = 15.dp, bottom = 30.dp)
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(IntrinsicSize.Min)
                                    .width(314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                ) {
                                    Column {
                                        Button(
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = colorResource(
                                                    id = com.example.project3activity.R.color.bg_splash
                                                )
                                            ),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .size(58.dp),

                                            onClick = {
                                                val unansweredQuestions =
                                                    selectedStates.count { it.isBlank() }

                                                if (unansweredQuestions > 0) {
                                                    Toast.makeText(
                                                        lContext,
                                                        lContext.getResources()
                                                            .getString(R.string.Skrinning_error),
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                } else {
                                                    if (selectedStates.count { it == "Ya" } > 0) {
                                                        var check = "1"
                                                        lContext.startActivity(
                                                            Intent(
                                                                lContext,
                                                                SkrinningProccessActivity::class.java
                                                            )
                                                                .putExtra("userId", userId)
                                                                .putExtra("check", check)
                                                        )
                                                    } else {
                                                        var check = "2"
                                                        lContext.startActivity(
                                                            Intent(
                                                                lContext,
                                                                SkrinningProccessActivity::class.java
                                                            )
                                                                .putExtra("userId", userId)
                                                                .putExtra("check", check)
                                                        )
                                                    }

                                                }
                                            },
                                        ) {
                                            Text(
                                                text = stringResource(id = com.example.project3activity.R.string.Skrinning_check),
                                                style = TextStyle(
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                                color = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun Question(questionText: String, selectedValue: String, choices: List<String>, onValueChanged: (String) -> Unit) {
    Text(questionText)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        choices.forEach { choice ->
            val value = selectedValue
            RadioButton(
                selected = value == choice,
                onClick = { onValueChanged(choice) },
                colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
            )
            Text(choice)
        }
    }
}

