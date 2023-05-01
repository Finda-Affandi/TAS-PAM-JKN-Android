package com.example.project3activity.ui.screens

import android.content.Intent
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.project3activity.ConsActivity
import com.example.project3activity.Firebase.AddDataToFirebase
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.HomeActivity
import com.example.project3activity.R
import com.example.project3activity.RecentActivity
import com.example.project3activity.models.ConsulModel
import com.google.firebase.auth.FirebaseAuth
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Composable
fun UpcomingActivity(viewModel: GetFirebaseData = viewModel()) {
    var lContext = LocalContext.current
    val consultation = viewModel.fetchRequestedConsultationData().observeAsState()

    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid.toString()


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

    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp),
        ) {

            Text(
                text = "Upcoming Activity",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize()
                .fillMaxHeight()
        ) {

            /*tombol kembali*/
            TextButton(
                onClick = {
                    lContext.startActivity(
                        Intent(lContext, HomeActivity::class.java)
                            .putExtra("dest", "profile")
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

            Spacer(modifier = Modifier.height(18.dp))


            LazyColumn(modifier = Modifier.height(720.dp).padding(16.dp)) {
                items(consultation.value ?: emptyList()) { consultation ->
                    if (consultation.userId == userId.toString()) {
//                        val inputDateString = consultation.date
//                        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//                        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
//
//                        val parsedDate = LocalDate.parse(inputDateString, inputFormatter)
//                        val formattedDate = parsedDate.format(outputFormatter)
//
//                        val inputTimeString = consultation.time
//                        val inputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
//                        val outputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
//
//                        val parsedTime = LocalTime.parse(inputTimeString, inputTimeFormatter)
//                        val formattedTime = parsedTime.format(outputTimeFormatter)

                        Button(
                            onClick = {
                                lContext.startActivity(
                                    Intent(lContext, RecentActivity::class.java)
                                        .putExtra("ConsulId", consultation.id)
                                        .putExtra("navBarState", "profile")
                                )
                            },
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
//                                    text = stringResource(id = R.string.History_Button_2_dr),
                                        text = consultation.doctor,
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
//                                        text = stringResource(id = R.string.Spec_2),
                                            text = consultation.speciality,
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
//                                        text = stringResource(id = R.string.Loc_2),
                                            text = consultation.location,
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
//                                        text = stringResource(id = R.string.WorkDay_2),
//                                        text = consultation.date + consultation.time,
                                            text = consultation.date + ", " + consultation.time,
//                                            text = formattedDate + ", " + formattedTime,
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
                                painter = painterResource(id = R.drawable.dr_2),
                                contentDescription = "Character-icon",
                                modifier = Modifier
                                    .size(250.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
//                    Spacer(modifier = Modifier.height(64.dp))
                }
            }
        }
    }
}