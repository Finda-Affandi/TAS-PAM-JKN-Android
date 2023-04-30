package com.example.project3activity.ui.screens


import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project3activity.HomeActivity
import com.example.project3activity.R

@Composable
fun InformasiKlinikScreen(userId : String){
    val lContext = LocalContext.current

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
            text = stringResource(id = R.string.label_icon7),
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



        LazyColumn(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxSize()
                .fillMaxHeight()
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp)
                ) {

                    //Konten
                    Image(
                        painter = painterResource(id = R.drawable.informasi_klinik),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 314.dp, height = 185.dp)
                            .shadow(4.dp, shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }
            }

            items(4) { index ->
                Column(
                    modifier = Modifier
                        .padding(top = 15.dp, start = 25.dp, bottom = 15.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .size(height = 80.dp, width = 314.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = Color.White)
                            .padding(start = 20.dp)
                    ) {
                        Row(modifier = Modifier.align(Alignment.CenterStart)) {
                            when (index) {
                                0 -> Icon(
                                    painter = painterResource(id = R.drawable.klinik_nama),
                                    modifier = Modifier.size(25.dp),
                                    contentDescription = "icon email"
                                )
                                1 -> Icon(
                                    imageVector = Icons.Rounded.Face,
                                    contentDescription = "icon email"
                                )
                                2 -> Icon(
                                    painter = painterResource(id = R.drawable.klinik_jam),
                                    modifier = Modifier.size(25.dp),
                                    contentDescription = "icon email"
                                )
                                3 -> Icon(
                                    painter = painterResource(id = R.drawable.klinik_call),
                                    modifier = Modifier.size(25.dp),
                                    contentDescription = "Key password"
                                )
                            }
                        }
                        Row(modifier = Modifier.padding(start = 40.dp).align(Alignment.CenterStart)) {
                            Column {
                                Text(
                                    text = when (index) {
                                        0 -> stringResource(id = R.string.InformasiKlinik1)
                                        1 -> stringResource(id = R.string.InformasiKlinik3)
                                        2 -> stringResource(id = R.string.InformasiKlinik5)
                                        3 -> stringResource(id = R.string.InformasiKlinik7)
                                        else -> ""
                                    },
                                    style = MaterialTheme.typography.overline
                                )
                                Text(
                                    text = when (index) {
                                        0 -> stringResource(id = R.string.InformasiKlinik2)
                                        1 -> stringResource(id = R.string.InformasiKlinik4)
                                        2 -> stringResource(id = R.string.InformasiKlinik6)
                                        3 -> stringResource(id = R.string.InformasiKlinik8)
                                        else -> ""
                                    },
                                    style = MaterialTheme.typography.subtitle2
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewInformasiKlinik() {
//    Project3activityTheme {
////        Greeting2("Android")
//        InformasiKlinikScreen()
//    }
//
//}