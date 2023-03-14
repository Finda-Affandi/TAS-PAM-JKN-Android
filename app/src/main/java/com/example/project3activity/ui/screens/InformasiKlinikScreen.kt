package com.example.project3activity.ui.screens


import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project3activity.HomeActivity
import com.example.project3activity.R
import com.example.project3activity.models.JknUserViewModel
import com.example.project3activity.ui.theme.Project3activityTheme

@Composable
fun InformasiKlinikScreen(vm : JknUserViewModel, userId : String){
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

    LaunchedEffect(
        Unit,
        block = {
            vm.getJknUserList()
        }
    )

    for (index in vm.jknUserList) {
        if (index.id.toString() == userId) {
            firstname = index.firstname
            lastname = index.lastname
            nik = index.nik
            lahir = index.lahir
            alamat = index.alamat
        }
    }

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



        Column(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxSize()
                .fillMaxHeight()
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ) {

//                Konten
                Image(
                    painter = painterResource(id = R.drawable.informasi_klinik),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 314.dp, height = 185.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )

                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Column(modifier = Modifier
                        .padding(top = 15.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .size(height = 80.dp, width = 314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                                    .padding(start = 20.dp)
                            ) {
                                Row (modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.klinik_nama),
                                        modifier = Modifier.size(25.dp),
                                        contentDescription = "icon email"
                                    )
                                }
                                Row (modifier = Modifier
                                    .padding(start = 40.dp)
                                    .align(Alignment.CenterStart)) {
                                    Column () {
                                        Column {
                                            Text(text = stringResource(id = R.string.InformasiKlinik1), style = MaterialTheme.typography.overline)
                                        }
                                        Column {
                                            Text(text = stringResource(id = R.string.InformasiKlinik2), style = MaterialTheme.typography.subtitle2)
                                        }
                                    }
                                }

                            }
                        }
                    }

                    Column(modifier = Modifier
                        .padding(top = 15.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .size(height = 80.dp, width = 314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                                    .padding(start = 20.dp)
                            ) {
                                Row (modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        imageVector = Icons.Rounded.Face,
                                        contentDescription = "icon email"
                                    )
                                }
                                Row (modifier = Modifier
                                    .padding(start = 40.dp)
                                    .align(Alignment.CenterStart)) {
                                    Column () {
                                        Column {
                                            Text(text = stringResource(id = R.string.InformasiKlinik3), style = MaterialTheme.typography.overline)
                                        }
                                        Column {
                                            Text(text = stringResource(id = R.string.InformasiKlinik4), style = MaterialTheme.typography.subtitle2)
                                        }
                                    }
                                }

                            }
                        }
                    }

                    Column(modifier = Modifier
                        .padding(top = 15.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .size(height = 80.dp, width = 314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                                    .padding(start = 20.dp)
                            ) {
                                Row (modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.klinik_jam),
                                        modifier = Modifier.size(25.dp),
                                        contentDescription = "icon email"
                                    )
                                }
                                Row (modifier = Modifier
                                    .padding(start = 40.dp)
                                    .align(Alignment.CenterStart)) {
                                    Column () {
                                        Column {
                                            Text(text = stringResource(id = R.string.InformasiKlinik5), style = MaterialTheme.typography.overline)
                                        }
                                        Column {
                                            Text(text = stringResource(id = R.string.InformasiKlinik6), style = MaterialTheme.typography.subtitle2)
                                        }
                                    }
                                }

                            }
                        }
                    }

                    Column(modifier = Modifier
                        .padding(top = 15.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .size(height = 80.dp, width = 314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                                    .padding(start = 20.dp)
                            ) {
                                Row (modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.klinik_call),
                                        modifier = Modifier.size(25.dp),
                                        contentDescription = "Key password"
                                    )
                                }
                                Row (modifier = Modifier
                                    .padding(start = 40.dp)
                                    .align(Alignment.CenterStart)) {
                                    Column () {
                                        Column {
                                            Text(text = stringResource(id = R.string.InformasiKlinik7), style = MaterialTheme.typography.overline)
                                        }
                                        Column {
                                            Text(
                                                text = stringResource(id = R.string.InformasiKlinik8),
                                                style = MaterialTheme.typography.subtitle2
                                            )
                                        }
                                    }
                                }

                            }
                        }
                    }

                    Column(modifier = Modifier
                        .padding(top = 15.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .size(height = 80.dp, width = 314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                                    .padding(start = 20.dp)
                            ) {
                                Row (modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.key_form),
                                        contentDescription = "Key password"
                                    )
                                }
                                Row (modifier = Modifier
                                    .padding(start = 40.dp)
                                    .align(Alignment.CenterStart)) {
                                    Column () {
                                        Column {
                                            Text(text = stringResource(id = R.string.label_reg6), style = MaterialTheme.typography.overline)
                                        }
                                        Column {
                                            Text(
                                                text = stringResource(id = R.string.InformasiKlinik9),
                                                style = MaterialTheme.typography.subtitle2
                                            )
                                        }
                                    }
                                }

                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))


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