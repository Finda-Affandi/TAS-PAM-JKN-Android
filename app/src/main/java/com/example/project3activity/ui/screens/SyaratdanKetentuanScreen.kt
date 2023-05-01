package com.example.project3activity.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project3activity.DaftarLayananActivity
import com.example.project3activity.R
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.*

@Composable
fun SyaratdanKetentuanScreen(userId : String , language: String) {

    val firestore = FirebaseFirestore.getInstance()
    val collectionName = "terms-condition"
    val documentName = if (language == "en") "OJmzdaCK8tPN26fQYJMc" else "pP9P1pxiCR7KYY4wo919"
    var text by remember { mutableStateOf("") }

    val lContext = LocalContext.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = R.drawable.pattern),
            contentDescription = "pattern",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 80.dp)
                .width(width = 400.dp)
                .height(height = 85.dp)
        )
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 120.dp),
    ) {
        Text(
            text = stringResource(id = R.string.Daftar_10),
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
                painter = painterResource(id = R.drawable.other_back),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp)
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
                                Column {

                                    LaunchedEffect(documentName) {
                                        firestore.collection(collectionName).document(documentName).get().addOnSuccessListener { document ->
                                            if (document.exists()) {
                                                text = document.getString("text") ?: ""
                                            }
                                        }
                                    }

                                    Text(text = text, textAlign = TextAlign.Justify, style = MaterialTheme.typography.subtitle2)
                                }
                            }

                        }
                    }
                }



                Spacer(modifier = Modifier.height(28.dp))





            }
        }
    }

}
