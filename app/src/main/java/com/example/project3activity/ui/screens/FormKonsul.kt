package com.example.project3activity.ui.screens

import android.content.Intent
import android.graphics.Paint.Style
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
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.HomeActivity
import com.example.project3activity.R
import com.example.project3activity.ui.BottomNavItems
private val daysList: List<String> = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
@Composable
fun DoctorConsultationDetails(viewModel: GetFirebaseData, DoctorId: String) {
    val lContext = LocalContext.current
    val DoctorData by viewModel.getConsulData(DoctorId!!)

    var timeinput by remember {
        mutableStateOf("")
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
//            AsyncImage(
//                model = ,
//                contentDescription = "Profile Picture",
//                modifier = Modifier
//                    .size(55.dp)
//                    .clip(CircleShape)
//                    .border(3.dp, Color.Gray, CircleShape),  // add a border (optional)
//
//                contentScale = ContentScale.Crop
//            )

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
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
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
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
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
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
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
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
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
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
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
                            Column () {
                                Column {
                                    Text(text = "Select Day", style = MaterialTheme.typography.overline)
                                }
                                Column {
//                                    Text(text = "${DoctorData?.workday}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
//                                    Button(onClick = { /*TODO*/ }) {
//                                        Text(text = "Select Date")
//
//                                    }

                                    daysList.forEach { days ->
                                        var checked by remember {
                                            mutableStateOf(false)
                                        }

                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Checkbox(
                                                checked = checked,
                                                onCheckedChange = { checked_ ->
                                                    checked = checked_
                                                })
                                            Text(text = days, modifier = Modifier.padding(start = 4.dp))
                                        }
                                    }
//                                    Row(verticalAlignment = Alignment.CenterVertically) {
//                                        Checkbox(
//                                            checked = checkBoxDay1State,
//                                            onCheckedChange = { checkBoxDay1State_ ->
//                                                checkBoxDay1State = checkBoxDay1State_
//                                            })
//                                        Text(text = "Monday", modifier = Modifier.padding(start = 4.dp))
//                                    }
//                                    Row(verticalAlignment = Alignment.CenterVertically) {
//                                        Checkbox(
//                                            checked = checkBoxDay2State,
//                                            onCheckedChange = { checkBoxDay2State_ ->
//                                                checkBoxDay2State = checkBoxDay2State_
//                                            })
//                                        Text(text = "Tuesday", modifier = Modifier.padding(start = 4.dp))
//                                    }
//                                    Row(verticalAlignment = Alignment.CenterVertically) {
//                                        Checkbox(
//                                            checked = checkBoxDay3State,
//                                            onCheckedChange = { checkBoxDay3State_ ->
//                                                checkBoxDay3State = checkBoxDay3State_
//                                            })
//                                        Text(text = "Wednesday", modifier = Modifier.padding(start = 4.dp))
//                                    }
//                                    Row(verticalAlignment = Alignment.CenterVertically) {
//                                        Checkbox(
//                                            checked = checkBoxDay4State,
//                                            onCheckedChange = { checkBoxDay4State_ ->
//                                                checkBoxDay4State = checkBoxDay4State_
//                                            })
//                                        Text(text = "Thursday", modifier = Modifier.padding(start = 4.dp))
//                                    }
//                                    Row(verticalAlignment = Alignment.CenterVertically) {
//                                        Checkbox(
//                                            checked = checkBoxDay5State,
//                                            onCheckedChange = { checkBoxDay5State_ ->
//                                                checkBoxDay5State = checkBoxDay5State_
//                                            })
//                                        Text(text = "Friday", modifier = Modifier.padding(start = 4.dp))
//                                    }
//                                    Row(verticalAlignment = Alignment.CenterVertically) {
//                                        Checkbox(
//                                            checked = checkBoxDay6State,
//                                            onCheckedChange = { checkBoxDay6State_ ->
//                                                checkBoxDay6State = checkBoxDay6State_
//                                            })
//                                        Text(text = "Saturday", modifier = Modifier.padding(start = 4.dp))
//                                    }
//                                    Row(verticalAlignment = Alignment.CenterVertically) {
//                                        Checkbox(
//                                            checked = checkBoxDay7State,
//                                            onCheckedChange = { checkBoxDay7State_ ->
//                                                checkBoxDay7State = checkBoxDay7State_
//                                            })
//                                        Text(text = "Sunday", modifier = Modifier.padding(start = 4.dp))
//                                    }
                                }
                            }
                        }

                    }
                }
            }



            Spacer(modifier = Modifier.height(16 .dp))

            Column(modifier = Modifier
                .padding(top = 4.dp)
//                .border(1.dp, color = colorResource(id = R.color.bg_splash), RoundedCornerShape(20.dp))
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(20.dp)
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
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
//                                    Text(text = "${DoctorData?.workday}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
//                                    Button(onClick = { /*TODO*/ }) {
//                                        Text(text = "Select Time")
//
//                                    }
                                    TextField(value = timeinput, onValueChange = {timeinput = it})
                                }
                            }
                        }

                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Button(onClick = { /*TODO*/ },
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