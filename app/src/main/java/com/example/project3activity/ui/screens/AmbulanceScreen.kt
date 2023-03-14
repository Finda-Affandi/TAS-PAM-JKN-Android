package com.example.project3activity.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project3activity.HomeActivity
import com.example.project3activity.R
import com.example.project3activity.models.JknUserViewModel

@Composable
fun Ambulance(vm : JknUserViewModel, userId : String) {

    LaunchedEffect(
        Unit,
        block = {
            vm.getJknUserList()
        }
    )

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

    for (index in vm.jknUserList) {
        if (index.id.toString() == userId) {
            firstname = index.firstname
            lastname = index.lastname
            nik = index.nik
            lahir = index.lahir
            alamat = index.alamat
        }
    }

    val lCOntext = LocalContext.current

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
            text = stringResource(id = R.string.ambulancepg_emergency),
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

        Spacer(modifier = Modifier.height(64.dp))

//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//
//            Button(
//                onClick = {
//                    Toast.makeText(
//                        lCOntext,
//                        lCOntext.getResources().getString(R.string.under_developing),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                },
//                shape = CircleShape,
//                modifier = Modifier.size(325.dp),
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = colorResource(
//                        id = R.color.red_ambulance
//                    )
//                )
//            ) {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ambulancce_icon_1),
//                        contentDescription = stringResource(
//                            id = R.string.ambulance
//                        ),
//                        tint = Color.White,
//                        modifier = Modifier.size(96.dp)
//                    )
//
//                    Spacer(modifier = Modifier.height(1.dp))
//
//                    Text(
//                        text = stringResource(id = R.string.SOS),
//                        color = Color.White,
//                        fontSize = 42.sp,
//                        style = TextStyle(fontWeight = FontWeight.SemiBold)
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                TextButton(onClick = { /*TODO*/ },) {
//                    Icon(
//                        imageVector = Icons.Rounded.LocationOn,
//                        contentDescription = "Loc",
//                        tint = Color.Black,
//                        modifier = Modifier.size(ButtonDefaults.IconSize)
//                    )
//                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//                    Text(
//                        text = stringResource(id = R.string.Amb_loc),
//                        style = TextStyle(
//                            textDecoration = TextDecoration.None,
//                            fontWeight = FontWeight.SemiBold
//                        ),
//                        color = Color.Black,
//                        fontSize = 16.sp
//                    )
//
//                }
//
//            }
//        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AmbulancePreview(){
//    Ambulance("coy")
//}