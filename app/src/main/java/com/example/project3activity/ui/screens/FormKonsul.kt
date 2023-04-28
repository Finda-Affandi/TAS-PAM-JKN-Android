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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun DoctorConsultationDetails(viewModel: GetFirebaseData, DoctorId: String) {
    val lContext = LocalContext.current
    val DoctorData by viewModel.getConsulData(DoctorId!!)

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
                                    Text(text = "Select Date", style = MaterialTheme.typography.overline)
                                }
                                Column {
//                                    Text(text = "${DoctorData?.workday}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                                    Button(onClick = { /*TODO*/ }) {
                                        Text(text = "Select Date")
                                        
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
                                    Button(onClick = { /*TODO*/ }) {
                                        Text(text = "Select Time")

                                    }
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