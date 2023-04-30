package com.example.project3activity.ui.screens

import android.content.Intent
import android.graphics.Paint.Style
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.runtime.*
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.project3activity.ConsActivity
import com.example.project3activity.Firebase.AddDataToFirebase
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.HomeActivity
import com.example.project3activity.R
import com.example.project3activity.models.ConsulModel
import com.example.project3activity.ui.BottomNavItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import kotlinx.coroutines.tasks.await
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

private val daysList: List<String> = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

suspend fun fetchWorkdays(): List<String>? {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("dr-consult").document("FOm7SvxZGrrweNgZWWIm")
    val document = docRef.get().await()

    return document.getString("detailsworkday")?.split(", ")
}
@Composable
fun DoctorConsultationDetails(viewModel: GetFirebaseData, DoctorId: String) {
    val lContext = LocalContext.current
    val DoctorData by viewModel.getConsulData(DoctorId!!)

    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid.toString()

    var timeinput by remember {
        mutableStateOf("")
    }

    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    var pickedTime by remember {
        mutableStateOf(LocalTime.now())
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd MMMM yyy").format(pickedDate)
        }
    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("HH:mm").format(pickedTime)
        }
    }



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

    Box(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .fillMaxSize()
        .fillMaxWidth()
        .fillMaxHeight()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 120.dp),
    ) {

        Text(
            text = "Select Doctor",
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
                lContext.startActivity(
                    Intent(lContext, ConsActivity::class.java)
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



        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(DoctorData?.img),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape((12.dp)))
                    .height(320.dp)// clip to the circle shape
                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))   // add a border (optional)
            )

            Spacer(modifier = Modifier.height(16 .dp))

            Column(modifier = Modifier
                .padding(top = 8.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(20.dp)
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .height(80.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = Color.White)
                            .padding(start = 20.dp)
                    ) {
                        Row (modifier = Modifier.align(Alignment.CenterStart)) {
                            Icon(
                                painter = painterResource(id = R.drawable.dr_icon_recent),
                                modifier = Modifier.size(32.dp),
                                contentDescription = "icon email"
                            )
                        }
                        Row (modifier = Modifier
                            .padding(start = 40.dp)
                            .align(Alignment.CenterStart)) {
                            Column () {
                                Column {
                                    Text(text = "Doctor Name", style = MaterialTheme.typography.overline)
                                }
                                Column {
                                    Text(text = "${DoctorData?.name}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                                }
                            }
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.height(16 .dp))

            Column(modifier = Modifier
                .padding(top = 4.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(20.dp)
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(color = Color.White)
                            .padding(start = 20.dp)
                    ) {
                        Row (modifier = Modifier.align(Alignment.CenterStart)) {
                            Icon(
                                painter = painterResource(id = R.drawable.info_pes_loc),
                                modifier = Modifier.size(32.dp),
                                contentDescription = "icon email"
                            )
                        }
                        Row (modifier = Modifier
                            .padding(start = 40.dp)
                            .align(Alignment.CenterStart)) {
                            Column () {
                                Column {
                                    Text(text = "Hospital", style = MaterialTheme.typography.overline)
                                }
                                Column {
                                    Text(text = "${DoctorData?.location}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                                }
                            }
                        }

                    }
                }
            }


            Spacer(modifier = Modifier.height(16 .dp))

            Column(modifier = Modifier
                .padding(top = 4.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(20.dp)
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(color = Color.White)
                            .padding(start = 20.dp)
                    ) {
                        Row (modifier = Modifier.align(Alignment.CenterStart)) {
                            Icon(
                                painter = painterResource(id = R.drawable.navbar_janji_temu),
                                contentDescription = "icon email"
                            )
                        }
                        Row (modifier = Modifier
                            .padding(start = 40.dp)
                            .align(Alignment.CenterStart)) {
                            Column () {
                                Column {
                                    Text(text = "Speciality", style = MaterialTheme.typography.overline)
                                }
                                Column {
                                    Text(text = "${DoctorData?.speciality}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                                }
                            }
                        }

                    }
                }
            }


            Spacer(modifier = Modifier.height(16 .dp))

            Column(modifier = Modifier
                .padding(top = 4.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(20.dp)
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(color = Color.White)
                            .padding(start = 20.dp)
                    ) {
                        Row (modifier = Modifier.align(Alignment.CenterStart)) {
                            Icon(
                                painter = painterResource(id = R.drawable.info_pes_date),
                                modifier = Modifier.size(32.dp),
                                contentDescription = "Workday"
                            )
                        }
                        Row (modifier = Modifier
                            .padding(start = 40.dp)
                            .align(Alignment.CenterStart)) {
                            Column () {
                                Column {
                                    Text(text = "Workday", style = MaterialTheme.typography.overline)
                                }
                                Column {
                                    Text(text = "${DoctorData?.workday}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                                }
                            }
                        }

                    }
                }
            }


            Spacer(modifier = Modifier.height(16 .dp))

            Column(modifier = Modifier
                .padding(top = 4.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(20.dp)
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .height(350.dp)
                            .background(color = Color.White)
                            .padding(start = 20.dp)
                    ) {
                        Row (modifier = Modifier.align(Alignment.CenterStart)) {
                            Icon(
                                painter = painterResource(id = R.drawable.info_pes_date),
                                modifier = Modifier.size(32.dp),
                                contentDescription = "Workday"
                            )
                        }
                        Row (modifier = Modifier
                            .padding(start = 40.dp)
                            .align(Alignment.CenterStart)) {
                            Column {
                                Column {
                                    Text(text = "Select Day", style = MaterialTheme.typography.overline)
                                }
                                val dateDialogState = rememberMaterialDialogState()
                                Column {
                                    Button(onClick = {
                                        dateDialogState.show()}) {
                                        Text(text = "Pick Date")
                                    }
                                    Text(text = formattedDate)


                                }
                                MaterialDialog(
                                    dialogState = dateDialogState,
                                    buttons = {
                                        positiveButton(text = "ok") {
                                            Toast.makeText(lContext, "Date Added", Toast.LENGTH_SHORT).show()
                                        }
                                        negativeButton(text = "cancel"){
                                            Toast.makeText(lContext, "Canceled", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                ) {
                                    title(text = "Avoid Selecting Weekend Days")
                                    datepicker(
                                        initialDate = LocalDate.now(),
                                        title = "pick a date",
                                        colors = DatePickerDefaults.colors(),
                                        allowedDateValidator = {
                                            date -> date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now())
//                                                date ->
//                                            val workdays = fetchWorkdays()!!
//                                            if (workdays != null) {
//                                                workdays.any { day ->
//                                                    DayOfWeek.valueOf(day.toString()) == date.dayOfWeek
//                                                }
//                                            } else {
//                                                false
//                                            }
                                        }
                                    ){
                                        pickedDate = it
                                    }
                                }
                            }
                        }

                    }
                }
            }



            Spacer(modifier = Modifier.height(16 .dp))

            Column(modifier = Modifier
                .padding(top = 4.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(20.dp)
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(color = Color.White)
                            .padding(start = 20.dp)

                    ) {
                        Row (modifier = Modifier.align(Alignment.CenterStart)) {
                            Icon(
                                painter = painterResource(id = R.drawable.info_pes_date),
                                modifier = Modifier.size(32.dp),
                                contentDescription = "Workday"
                            )
                        }
                        Row (modifier = Modifier
                            .padding(start = 40.dp)
                            .align(Alignment.CenterStart)) {
                            Column () {
                                Column {
                                    Text(text = "Select Time", style = MaterialTheme.typography.overline)
                                }
                                Column {
                                    val timeDialogState = rememberMaterialDialogState()
                                    Button(onClick = {timeDialogState.show()}) {
                                        Text(text = "Pick Time")
                                    }
                                    Text(text = formattedTime)

                                    MaterialDialog(
                                        dialogState = timeDialogState,
                                        buttons = {
                                            positiveButton(text = "ok") {
                                                Toast.makeText(lContext, "Time Added", Toast.LENGTH_SHORT).show()
                                            }
                                            negativeButton(text = "cancel"){
                                                Toast.makeText(lContext, "Canceled", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    ) {
                                            title(text = "Select Time from 9 AM - 4 PM")
                                        timepicker(
                                            is24HourClock = true,
                                            initialTime = LocalTime.now(),
                                            title = "pick a time",
                                            colors = TimePickerDefaults.colors(),
                                            timeRange = LocalTime.of(9,0)..LocalTime.of(16,0),
                                        ){
                                            pickedTime = it
                                        }
                                    }
                                }

                            }
                        }

                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Button(
                onClick = {
                    var doctorName = DoctorData?.name.toString()
                    var speciality = DoctorData?.speciality.toString()
                    var location = DoctorData?.location.toString()
                    var date = pickedDate.toString()
                    var time = pickedTime.toString()
                    var docId = UUID.randomUUID().toString()
                    val errorToast = Toast.makeText(lContext,"Failed to make appointment", Toast.LENGTH_SHORT)
                    val addToFirebase = AddDataToFirebase()
                    addToFirebase.addConsulDataToFirebase(
                        ConsulModel(docId, userId, doctorName, speciality, location, date, time),
                        { consulModel ->
                           if (consulModel != null) {
                               lContext.startActivity(
                                   Intent(lContext, HomeActivity::class.java)
                                       .putExtra("userId", userId)
                               )
                           }
                           else {
                               errorToast.show()
                           }
                        },
                        {
                            errorToast.show()
                        }
                    )
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(64.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                )) {
                Text(text = "Make Appointment", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color.White))
            }

            Spacer(modifier = Modifier.height(14.dp))
            

        }
    }

    }

}