package com.example.project3activity.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project3activity.HomeActivity
import com.example.project3activity.R

@Composable
fun Recent() {
    var lCOntext = LocalContext.current

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
            text = stringResource(id = R.string.label_icon5),
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



//            Box info 1
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 10.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.White)
                ) {
                    Row(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.location_icon_recent),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 60.dp)
                            .align(Alignment.CenterStart)
                    ) {
                        Column() {
                            Text(text = stringResource(id = R.string.Rec_1), style = MaterialTheme.typography.overline)

                            Text(text = "RS itu", style = MaterialTheme.typography.subtitle2)

                        }

                    }
                }
            }

                    }


        //            Box info 1
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 10.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.White)
                ) {
                    Row(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.time_icon_recent),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 60.dp)
                            .align(Alignment.CenterStart)
                    ) {
                        Column() {
                            Text(text = stringResource(id = R.string.Rec_2), style = MaterialTheme.typography.overline)

                            Text(text = "17:15, 26-februari-2023", style = MaterialTheme.typography.subtitle2)

                        }

                    }
                }
            }

        }


        //            Box info 1
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 10.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.White)
                ) {
                    Row(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.dr_icon_recent),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 60.dp)
                            .align(Alignment.CenterStart)
                    ) {
                        Column() {
                            Text(text = stringResource(id = R.string.Rec_3), style = MaterialTheme.typography.overline)

                            Text(text = "Dr. Boyke icikiwir", style = MaterialTheme.typography.subtitle2)

                        }

                    }
                }
            }

        }


        //            Box info 1
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 10.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.White)
                ) {
                    Row(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.type),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 60.dp)
                            .align(Alignment.CenterStart)
                    ) {
                        Column() {
                            Text(text = stringResource(id = R.string.Rec_5), style = MaterialTheme.typography.overline)

                            Text(text = "Konsultasi Umum", style = MaterialTheme.typography.subtitle2)

                        }

                    }
                }
            }

        }


        //            Box info 1
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 10.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.White)
                ) {
                    Row(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.diagnostic),
                            modifier = Modifier.size(36.dp),
                            contentDescription = null
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 60.dp, end = 16.dp)
                            .align(Alignment.CenterStart)
                    ) {
                        Column() {
                            Text(text = stringResource(id = R.string.Rec_4), style = MaterialTheme.typography.overline)

                            Text(text = "Kurang minum, kurang asupan zat gizi, zat besi, protein, dan lemak. Pasien disarankan untuk banyak minum air putih dan minuman penambah ion tubuh.", style = MaterialTheme.typography.subtitle2)

                        }

                    }
                }
            }

        }

                }
            }

@Preview(showBackground = true)
@Composable
fun previewRecent(){
    Recent()
}