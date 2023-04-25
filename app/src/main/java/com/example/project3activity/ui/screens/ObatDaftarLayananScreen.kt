package com.example.project3activity.ui.screens


import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.compose.material.Button
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.project3activity.DaftarLayananActivity
import com.example.project3activity.HomeActivity
import com.example.project3activity.ObatDaftarLayanan


@Composable
fun ObatDaftarLayananScreen(userId : String) {
    val lContext = LocalContext.current
    val downloadLink = "https://bpjs-kesehatan.go.id/bpjs/dmdocuments/a85e064985b713815222f9a4ca213f94.pdf"

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode != Activity.RESULT_OK) {
            Toast.makeText(
                lContext,
                "Unduh gagal",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = com.example.project3activity.R.drawable.pattern),
            contentDescription = "pattern",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 80.dp)
                .width(width = 400.dp)
                .height(height = 85.dp)
        )
    }

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
            text = stringResource(id = com.example.project3activity.R.string.Daftar_9),
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
                    Intent(lContext, DaftarLayananActivity::class.java)
                        .putExtra("userId", userId)
                )
            },
            modifier = Modifier.padding(start = 20.dp)

        ) {
            Image(
                painter = painterResource(id = com.example.project3activity.R.drawable.other_back),
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
                                val intent = Intent(Intent.ACTION_VIEW, downloadLink.toUri())
                                launcher.launch(intent)
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
                                    painter = painterResource(id = com.example.project3activity.R.drawable.obat_daftar),
                                    contentDescription = null,
                                    modifier = a4
                                )
                                Text(
                                    text = stringResource(id = com.example.project3activity.R.string.Daftar_8),
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