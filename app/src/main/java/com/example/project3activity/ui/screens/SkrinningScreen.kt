package com.example.project3activity.ui.screens


import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.compose.material.Button
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
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

@Composable
fun SkrinningScreen(userId : String) {

    val choices = listOf("Ya", "Tidak")
    val selectedStates = remember { mutableStateListOf("", "", "", "", "") }
    var showToast by remember { mutableStateOf(false) }

    val lContext = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = com.example.project3activity.R.drawable.pattern),
            contentDescription = "pattern",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 80.dp)
                .width(width = 400.dp)
                .height(height = 85.dp)
        )
    }

    val button_xl = Modifier
        .size(width = 314.dp, height = 120.dp)
        .shadow(4.dp, shape = RoundedCornerShape(20.dp))
        .defaultMinSize(1.dp, minHeight = 1.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 120.dp),
    ) {
        Text(
            text = stringResource(id = com.example.project3activity.R.string.Daftar_9),
            color = Color.Black,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
        )
    }


    Column(modifier = Modifier
        .padding(top = 100.dp)
        .fillMaxSize()
        .fillMaxHeight()) {

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

                Column(modifier = Modifier
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(20.dp)
                    )
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(IntrinsicSize.Min)
                                .width(314.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color.White)
                                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp, end = 15.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Row (modifier = Modifier
                                .align(Alignment.CenterStart)) {
                                Column{
                                    Column {
                                        Question(
                                            "Apakah Anda suka olahraga?",
                                            selectedStates[0],
                                            choices
                                        ) {
                                            selectedStates[0] = it
                                        }

                                        Question(
                                            "Apakah Anda sering makan junk food?",
                                            selectedStates[1],
                                            choices
                                        ) {
                                            selectedStates[1] = it
                                        }

                                        Question(
                                            "Apakah Anda pernah merokok?",
                                            selectedStates[2],
                                            choices
                                        ) {
                                            selectedStates[2] = it
                                        }

                                        Question(
                                            "Apakah Anda sering minum alkohol?",
                                            selectedStates[3],
                                            choices
                                        ) {
                                            selectedStates[3] = it
                                        }

                                        Question(
                                            "Apakah Anda pernah berpuasa?",
                                            selectedStates[4],
                                            choices
                                        ) {
                                            selectedStates[4] = it
                                        }
                                    }
                                    Column {
                                        Button(
                                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = com.example.project3activity.R.color.bg_splash)),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .size(58.dp),

                                            onClick = {
//                                                showToast = selectedStates.count { it == "Ya" } > 2 || selectedStates.count { it == "Tidak" } > 2
                                                if (selectedStates.count { it == "Ya" } > 2) {
                                                    lContext.startActivity(
                                                        Intent(
                                                            lContext,
                                                            DaftarLayananActivity::class.java
                                                        )
                                                            .putExtra("userId", userId)
                                                    )
                                                } else if (selectedStates.count { it == "Tidak" } > 2) {
                                                    Toast.makeText(
                                                        lContext, lContext.getResources().getString(
                                                            com.example.project3activity.R.string.WorkDay_1
                                                        ), Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            },
                                        ) {
                                            Text(
                                                text = stringResource(id = com.example.project3activity.R.string.label_regjkn),
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

//                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//
//                    Column(
//                        modifier = Modifier
//                            .padding(top = 15.dp)
//                            .shadow(
//                                elevation = 2.dp,
//                                shape = RoundedCornerShape(20.dp)
//                            )
//                    ) {
//                        Column {
//                            Box(
//                                modifier = Modifier
//                                    .height(IntrinsicSize.Min)
//                                    .width(314.dp)
//                                    .clip(RoundedCornerShape(10.dp))
//                                    .background(color = Color.White)
//                                    .padding(
//                                        start = 15.dp,
//                                        top = 15.dp,
//                                        bottom = 15.dp,
//                                        end = 15.dp
//                                    )
//                            ) {
//                                Row(
//                                    modifier = Modifier
//                                        .padding(start = 40.dp)
//                                        .align(Alignment.CenterStart)
//                                ) {
//                                    Column(
//                                        modifier = Modifier.padding(16.dp)
//                                    ) {
//                                        Question("Apakah Anda suka olahraga?", selectedStates[0], choices) {
//                                            selectedStates[0] = it
//                                        }
//
//                                        Question("Apakah Anda sering makan junk food?", selectedStates[1], choices) {
//                                            selectedStates[1] = it
//                                        }
//
//                                        Question("Apakah Anda pernah merokok?", selectedStates[2], choices) {
//                                            selectedStates[2] = it
//                                        }
//
//                                        Question("Apakah Anda sering minum alkohol?", selectedStates[3], choices) {
//                                            selectedStates[3] = it
//                                        }
//
//                                        Question("Apakah Anda pernah berpuasa?", selectedStates[4], choices) {
//                                            selectedStates[4] = it
//                                        }
//
//
//                                    }
//
//
//                                }
//
//                            }
//                        }
//                        Spacer(modifier = Modifier.height(28.dp))
//                    }
//
//                }
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

