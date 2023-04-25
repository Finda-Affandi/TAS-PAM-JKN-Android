package com.example.project3activity.ui.screens


import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project3activity.HomeActivity
import com.example.project3activity.ObatDaftarLayanan
import com.example.project3activity.R
import com.example.project3activity.RecentActivity
import com.example.project3activity.models.JknUserViewModel

@Composable
fun DaftarScreen(vm : JknUserViewModel, userId : String){

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

    LaunchedEffect(
        Unit,
        block = {
            vm.getJknUserList()
        }
    )

//    for (index in vm.jknUserList) {
//        if (index.id.toString() == userId) {
//            firstname = index.firstname
//            lastname = index.lastname
//            nik = index.nik
//            lahir = index.lahir
//            alamat = index.alamat
//        }
//    }

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

    val button_big = Modifier
        .size(width = 150.dp, height = 120.dp)
        .defaultMinSize(1.dp, minHeight = 1.dp)
        .shadow(4.dp, shape = RoundedCornerShape(20.dp))

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
            text = stringResource(id = R.string.label_icon6),
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
                    Intent(lContext, HomeActivity::class.java)
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
                modifier = Modifier.fillMaxWidth()
            ) {

//                Konten


                Row() {
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        val a4 = Modifier
                            .size(50.dp)
                        Button(
                            onClick = {
                                lContext.startActivity(
                                    Intent(lContext, ObatDaftarLayanan::class.java)
                                        .putExtra("userId", userId)
                                )
                            },
                            modifier = button_big,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffffffff)),

                            ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.daftar_obat),
                                    contentDescription = null,
                                    modifier = a4
                                )
                                Text(
                                    text = stringResource(id = R.string.Daftar_5),
                                    style = MaterialTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 14.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        val a4 = Modifier
                            .size(50.dp)
                        Button(
                            onClick = {
                                Toast.makeText(lContext, lContext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show()
                            },
                            modifier = button_big,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffffffff)),

                            ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.daftar_syarat),
                                    contentDescription = null,
                                    modifier = a4
                                )
                                Text(
                                    text = stringResource(id = R.string.Daftar_6),
                                    style = MaterialTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                    }

                }

                Row() {
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        val a4 = Modifier
                            .size(50.dp)
                        Button(
                            onClick = {
                                Toast.makeText(lContext, lContext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show()
                            },
                            modifier = button_xl,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffffffff)),

                            ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.dr_3),
                                    contentDescription = null,
                                    modifier = a4
                                )
                                Text(
                                    text = stringResource(id = R.string.Daftar_1),
                                    style = MaterialTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                    }


                }

                Row() {
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        val a4 = Modifier
                            .size(50.dp)
                        Button(
                            onClick = {
                                Toast.makeText(lContext, lContext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show()
                            },
                            modifier = button_xl,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffffffff)),

                            ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.daftar_virus),
                                    contentDescription = null,
                                    modifier = a4
                                )
                                Text(
                                    text = stringResource(id = R.string.Daftar_7),
                                    style = MaterialTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                    }


                }

            }
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun PreviewProfile() {
//    Project3activityTheme {
////        Greeting2("Android")
//        OtherPage()
//    }
//
//}