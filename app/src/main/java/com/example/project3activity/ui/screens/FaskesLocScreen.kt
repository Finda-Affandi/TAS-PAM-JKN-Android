package com.example.project3activity.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.*
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
import com.example.project3activity.HomeActivity
import com.example.project3activity.R
import com.example.project3activity.models.FaskesViewModel

@Composable
fun FaskesLoc(vf : FaskesViewModel, userId : String) {

    val lCOntext = LocalContext.current

    var nama by remember {
        mutableStateOf("")
    }

    var alamat by remember {
        mutableStateOf("")
    }

    var image by remember {
        mutableStateOf("")
    }

    LaunchedEffect(
        Unit,
        block = {
            vf.getFaskesList()
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
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
            text = stringResource(id = R.string.label_icon3),
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
                lCOntext.startActivity(
                    Intent(lCOntext, HomeActivity::class.java)
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


//        if (vf.errorMessage.isEmpty()) {
//            LazyColumn(modifier = Modifier.padding(18.dp)){
//                items(vf.faskesList.size) {index ->
//                    Text(text = vf.faskesList[index].nama)
//
//
//
//                }
//            }
//        }
//
//
//        else {
//            Text(text = vf.errorMessage)
//        }


        Spacer(modifier = Modifier.height(24.dp))

        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(
                rememberScrollState()
            )) {

            Button(
                onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(124.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                )
            ){

                Column(verticalArrangement = Arrangement.Center) {
                    Row(horizontalArrangement = Arrangement.Start ) {
                            Image(painter = painterResource(id = R.drawable.puskesmas_1), contentDescription = null, modifier = Modifier
                                .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                                .size(124.dp), contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = stringResource(id = R.string.FaskesName1), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp))
                            Text(text = stringResource(id = R.string.FaskesLoc1), style = TextStyle(fontWeight = FontWeight.Normal), fontSize = 12.sp)

                            Spacer(modifier = Modifier.height(4.dp))
                            Column(verticalArrangement = Arrangement.Bottom) {

                                Button(onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show() }, modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)), elevation = ButtonDefaults.elevation(
                                    defaultElevation = 4.dp,
                                    pressedElevation = 15.dp,
                                    disabledElevation = 0.dp,
                                    hoveredElevation = 15.dp,
                                    focusedElevation = 10.dp
                                )) {
                                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = Color.White)

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(text = stringResource(id = R.string.FaskesKontak), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
                }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(124.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                )
            ){

                Column(verticalArrangement = Arrangement.Center) {
                    Row(horizontalArrangement = Arrangement.Start, ) {
                        Image(painter = painterResource(id = R.drawable.puskesmas_2), contentDescription = null, modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                            .size(124.dp), contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = stringResource(id = R.string.FaskesName2), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp))
                            Text(text = stringResource(id = R.string.FaskesLoc2), style = TextStyle(fontWeight = FontWeight.Normal), fontSize = 12.sp)

                            Spacer(modifier = Modifier.height(4.dp))
                            Column(verticalArrangement = Arrangement.Bottom) {

                                Button(onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show() }, modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)), elevation = ButtonDefaults.elevation(
                                    defaultElevation = 4.dp,
                                    pressedElevation = 15.dp,
                                    disabledElevation = 0.dp,
                                    hoveredElevation = 15.dp,
                                    focusedElevation = 10.dp
                                )) {
                                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = Color.White)

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(text = stringResource(id = R.string.FaskesKontak), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(124.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                )
            ){

                Column(verticalArrangement = Arrangement.Center) {
                    Row(horizontalArrangement = Arrangement.Start, ) {
                        Image(painter = painterResource(id = R.drawable.puskesmas_3), contentDescription = null, modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                            .size(124.dp), contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = stringResource(id = R.string.FaskesName3), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp))
                            Text(text = stringResource(id = R.string.FaskesLoc3), style = TextStyle(fontWeight = FontWeight.Normal), fontSize = 12.sp)

                            Spacer(modifier = Modifier.height(4.dp))
                            Column(verticalArrangement = Arrangement.Bottom) {

                                Button(onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show() }, modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)), elevation = ButtonDefaults.elevation(
                                    defaultElevation = 4.dp,
                                    pressedElevation = 15.dp,
                                    disabledElevation = 0.dp,
                                    hoveredElevation = 15.dp,
                                    focusedElevation = 10.dp
                                )) {
                                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = Color.White)

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(text = stringResource(id = R.string.FaskesKontak), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(124.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                )
            ){

                Column(verticalArrangement = Arrangement.Center) {
                    Row(horizontalArrangement = Arrangement.Start, ) {
                        Image(painter = painterResource(id = R.drawable.klinik), contentDescription = null, modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                            .size(124.dp), contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = stringResource(id = R.string.FaskesName4), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp))
                            Text(text = stringResource(id = R.string.FaskesLoc4), style = TextStyle(fontWeight = FontWeight.Normal), fontSize = 12.sp)

                            Spacer(modifier = Modifier.height(4.dp))
                            Column(verticalArrangement = Arrangement.Bottom) {

                                Button(onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show() }, modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)), elevation = ButtonDefaults.elevation(
                                    defaultElevation = 4.dp,
                                    pressedElevation = 15.dp,
                                    disabledElevation = 0.dp,
                                    hoveredElevation = 15.dp,
                                    focusedElevation = 10.dp
                                )) {
                                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = Color.White)

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(text = stringResource(id = R.string.FaskesKontak), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(124.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                )
            ){

                Column(verticalArrangement = Arrangement.Center) {
                    Row(horizontalArrangement = Arrangement.Start, ) {
                        Image(painter = painterResource(id = R.drawable.puskesmas_4), contentDescription = null, modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(6.dp))
                            .size(124.dp), contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = stringResource(id = R.string.FaskesName5), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp))
                            Text(text = stringResource(id = R.string.FaskesLoc5), style = TextStyle(fontWeight = FontWeight.Normal), fontSize = 12.sp)

                            Spacer(modifier = Modifier.height(4.dp))
                            Column(verticalArrangement = Arrangement.Bottom) {

                                Button(onClick = { Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show() }, modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)), elevation = ButtonDefaults.elevation(
                                    defaultElevation = 4.dp,
                                    pressedElevation = 15.dp,
                                    disabledElevation = 0.dp,
                                    hoveredElevation = 15.dp,
                                    focusedElevation = 10.dp
                                )) {
                                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = Color.White)

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(text = stringResource(id = R.string.FaskesKontak), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


        }
            }
            
        }

//@Preview(showBackground = true)
//@Composable
//fun PreviewFaskesLoc(){
//    FaskesLoc()
//}