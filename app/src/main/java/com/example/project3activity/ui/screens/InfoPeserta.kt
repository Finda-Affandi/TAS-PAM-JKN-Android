package com.example.project3activity.ui.screens


import android.content.Intent
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.HomeActivity
import com.example.project3activity.R
import com.example.project3activity.UpcomingProfileActivity
import com.google.firebase.auth.FirebaseAuth


@Composable
fun InfoPeserta(
    viewModel: GetFirebaseData = viewModel(),
    onSubmitActionEvent: (img: ImageBitmap, caption: String) -> Unit,
    navBarState: String
){
    val lCOntext = LocalContext.current

    var captionText by remember { mutableStateOf("") }
    var takenImage by remember {
        mutableStateOf(
            BitmapFactory.decodeResource(lCOntext.resources, R.drawable.other_2).asImageBitmap()
        )
    }

    val takePictureContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { _takenImageBitmap ->
            takenImage = _takenImageBitmap!!.asImageBitmap()
        }
    )

    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid

    val jknUserData by viewModel.getJknPatientData(userId!!)


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
        .size(width = 315.dp, height = 100.dp)
        .defaultMinSize(1.dp, minHeight = 1.dp)
        .shadow(4.dp, shape = RoundedCornerShape(8.dp))

    val button = Modifier
        .size(width = 150.dp, height = 100.dp)
        .shadow(4.dp, shape = RoundedCornerShape(20.dp))
        .defaultMinSize(1.dp, minHeight = 1.dp)

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
                if (navBarState == "profile") {
                    lCOntext.startActivity(
                        Intent(lCOntext, HomeActivity()::class.java)
                            .putExtra("dest", "profile")
//                        .putExtra("userId", userId)
                    )
                } else {
                    lCOntext.startActivity(
                        Intent(lCOntext, HomeActivity::class.java)
//                        .putExtra("userId", userId)
                    )
                }
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
                .padding(top = 15.dp)
                .fillMaxSize()
                .fillMaxHeight()
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

//                image
//                Image(
//                    painter = painterResource(R.drawable.other_2),
//                    contentDescription = "avatar",
//                    contentScale = ContentScale.Fit,            // crop the image if it's not a square
//                    modifier = Modifier
//                        .size(150.dp)
//                        .clip(CircleShape)                       // clip to the circle shape
//                        .border(5.dp, Color.Gray, CircleShape)   // add a border (optional)
//                )
                Image(
                    painter = rememberAsyncImagePainter(jknUserData?.photoUrl),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(5.dp, Color.Gray, CircleShape)
                )

//                Konten
                Column() {

                    Column(modifier = Modifier
                        .padding(top = 15.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(height = 60.dp, width = 314.dp)
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
                                            Text(text = stringResource(id = R.string.label_reg1), style = MaterialTheme.typography.overline)
                                        }
                                        Column {
                                            jknUserData?.let { Text(text = it.firstname, style = MaterialTheme.typography.subtitle2) }
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
                                    .size(height = 60.dp, width = 314.dp)
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
                                            Text(text = stringResource(id = R.string.label_reg2), style = MaterialTheme.typography.overline)
                                        }
                                        Column {
                                            jknUserData?.let { Text(text = it.lastname, style = MaterialTheme.typography.subtitle2) }
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
                                    .size(height = 60.dp, width = 314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                                    .padding(start = 20.dp)
                            ) {
                                Row (modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.info_pes_nik),
                                        modifier = Modifier.size(25.dp),
                                        contentDescription = "Key password"
                                    )
                                }
                                Row (modifier = Modifier
                                    .padding(start = 40.dp)
                                    .align(Alignment.CenterStart)) {
                                    Column () {
                                        Column {
                                            Text(text = stringResource(id = R.string.label_reg3), style = MaterialTheme.typography.overline)
                                        }
                                        Column {
                                            jknUserData?.let { Text(text = it.nik, style = MaterialTheme.typography.subtitle2,) }
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
                                    .size(height = 60.dp, width = 314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                                    .padding(start = 20.dp)
                            ) {
                                Row (modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.info_pes_date),
                                        modifier = Modifier.size(25.dp),
                                        contentDescription = "icon email"
                                    )
                                }
                                Row (modifier = Modifier
                                    .padding(start = 40.dp)
                                    .align(Alignment.CenterStart)) {
                                    Column () {
                                        Column {
                                            Text(text = stringResource(id = R.string.label_reg5), style = MaterialTheme.typography.overline)
                                        }
                                        Column {
                                            jknUserData?.let { Text(text = it.lahir, style = MaterialTheme.typography.subtitle2) }
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
                                    .size(height = 60.dp, width = 314.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White)
                                    .padding(start = 20.dp)
                            ) {
                                Row (modifier = Modifier.align(Alignment.CenterStart)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.info_pes_loc),
                                        modifier = Modifier.size(25.dp),
                                        contentDescription = "icon email"
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
                                            jknUserData?.let { Text(text = it.alamat, style = MaterialTheme.typography.subtitle2) }
                                        }
                                    }
                                }

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
//fun PreviewProfile2() {
//    Project3activityTheme {
////        Greeting2("Android")
////        OtherPage()
//        InfoPeserta(username = "so", password = "mad" , firstname = "oh" , lastname = "as")
//    }
//
//}