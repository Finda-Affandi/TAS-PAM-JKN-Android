package com.example.project3activity.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project3activity.ArticleDetailsActivity
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.FormKonsulActivity
import com.example.project3activity.HomeActivity
import com.example.project3activity.R

@Composable
fun Konsul(viewModel: GetFirebaseData = viewModel()){

    val doctors = viewModel.fetchDoctorConsultationData().observeAsState()

    val lCOntext = LocalContext.current

    var firstname by remember {
        mutableStateOf("")
    }

    var lastname by remember {
        mutableStateOf("")
    }

    var nik by remember {
        mutableStateOf("")
    }

    var lahir by remember {
        mutableStateOf("")
    }

    var alamat by remember {
        mutableStateOf("")
    }


//    for (index in vm.jknUserList) {
//        if (index.id.toString() == userId) {
//            firstname = index.firstname
//            lastname = index.lastname
//            nik = index.nik
//            lahir = index.lahir
//            alamat = index.alamat
//        }
//    }

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
            text = stringResource(id = R.string.label_icon2),
            color = Color.Black,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
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
                lCOntext.startActivity(
                    Intent(lCOntext, HomeActivity::class.java)
//                        .putExtra("userId", userId)
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
        
        Spacer(modifier = Modifier.height(24.dp))

        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
//            .verticalScroll(
//                rememberScrollState()
//            )
        ) {

            LazyColumn{
                items(doctors.value ?: emptyList()){ doctor ->
                    Button(
                        onClick = { lCOntext.startActivity(
                            Intent(lCOntext, FormKonsulActivity::class.java)
                                .putExtra("DoctorId", doctor.id)
                        )},
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .height(108.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 20.dp,
                            pressedElevation = 15.dp,
                            disabledElevation = 0.dp,
                            hoveredElevation = 15.dp,
                            focusedElevation = 10.dp
                        )
                    )
                    {

                        Row(
                            modifier = Modifier
                                .width(275.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.Start) {
                                Text(
//                                    text = stringResource(id = R.string.History_Button_1_dr),
                                    text = doctor.name,
                                    color = Color.White,
                                    textAlign = TextAlign.Left,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 2.dp)
                                )

                                Spacer(modifier = Modifier.height(3.dp))
                                Row(
                                    modifier = Modifier
                                        .padding(top = 2.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.dr_icon_recent),
                                        contentDescription = "Vector", tint = Color.White
                                    )

                                    Text(
//                                        text = stringResource(id = R.string.Spec_1),
                                        text = doctor.speciality,
                                        color = Color.White,
                                        style = TextStyle(
                                            fontSize = 12.sp
                                        ),
                                        modifier = Modifier
                                            .padding(start = 6.dp, top = 3.dp)

                                    )
                                }

                                Spacer(modifier = Modifier.height(2.dp))

                                Row(
                                    modifier = Modifier
                                        .padding(top = 2.dp, start = 3.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.location_icon_recent),
                                        contentDescription = "Location", tint = Color.White
                                    )

                                    Text(
//                                        text = stringResource(id = R.string.Loc_1),
                                        text = doctor.location,
                                        color = Color.White,
//                    lineHeight = 95.sp,
                                        style = TextStyle(
                                            fontSize = 12.sp
                                        ),
                                        modifier = Modifier
                                            .padding(start = 6.dp)
//                            .fillMaxWidth()

                                    )
                                }
                                Spacer(modifier = Modifier.height(3.dp))
                                Row(
                                    modifier = Modifier
                                        .padding(top = 2.dp, start = 2.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.time_icon_recent),
                                        contentDescription = "Time", tint = Color.White
                                    )

                                    Text(
//                                        text = stringResource(id = R.string.WorkDay_1),
                                        text = doctor.workday,
                                        color = Color.White,
//                    lineHeight = 95.sp,
                                        style = TextStyle(
                                            fontSize = 12.sp
                                        ),
                                        modifier = Modifier
                                            .padding(start = 5.dp)
//                            .fillMaxWidth()

                                    )
                                }

                            }
                        }

                        Image(
                            painter = painterResource(id = R.drawable.character_1),
                            contentDescription = "Character-icon",
                            modifier = Modifier
                                .size(250.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

    }

}
