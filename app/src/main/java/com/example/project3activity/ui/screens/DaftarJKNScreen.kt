package com.example.project3activity.ui.screens


import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project3activity.Firebase.AddDataToFirebase
import com.example.project3activity.HomeActivity
import com.example.project3activity.MainActivity
import com.example.project3activity.R
import com.example.project3activity.models.*
import com.example.project3activity.ui.theme.Project3activityTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
//fun RegJKN(vj : JknUserViewModel, userId : String, onSubmitActionEvent: (img: ImageBitmap, caption: String) -> Unit) {
fun RegJKN(vj : JknUserViewModel, userId : String, onSubmitActionEvent: (img: ImageBitmap, caption: String) -> Unit) {
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


    val uid = userId

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
            vj.getJknUserList()
        }
    )

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
            text = stringResource(id = R.string.label_icon4),
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

        TextButton(
            onClick = {
                lCOntext.startActivity(
                    Intent(lCOntext, HomeActivity::class.java)
                        .putExtra("userId", userId),
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
//            .fillMaxSize()
                .padding((20.dp)),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {

//            Image(bitmap = takenImage, contentDescription = "",
//                modifier = Modifier
//                    .size(120.dp)
//                    .padding(end = 4.dp)
//                    .clickable {
//                        takePictureContract.launch()
//                    })

            OutlinedTextField(
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = colorResource(id = R.color.bg_splash),
                    unfocusedBorderColor = Color.Gray,
//                disabledIndicatorColor = Color.Transparent
                ),
//            draw
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Face,
                        contentDescription = "icon email"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                value = firstname,
                onValueChange = { firstname = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_reg1),
                        style = TextStyle(
                            color = colorResource(id = R.color.bg_splash),
                            fontSize = 16.sp
                        )
                    )
                }

            )


            OutlinedTextField(
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = colorResource(id = R.color.bg_splash),
                    unfocusedBorderColor = Color.Gray,
//                disabledIndicatorColor = Color.Transparent
                ),
//            draw
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.Face, contentDescription = "icon email")
                },
                modifier = Modifier
                    .fillMaxWidth(),
                value = lastname,
                onValueChange = { lastname = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_reg2),
                        style = TextStyle(
                            color = colorResource(id = R.color.bg_splash),
                            fontSize = 16.sp
                        )
                    )
                }
            )



            OutlinedTextField(
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = colorResource(id = R.color.bg_splash),
                    unfocusedBorderColor = Color.Gray,
//                disabledIndicatorColor = Color.Transparent
                ),
//            draw
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.info_pes_nik),
                        modifier = Modifier.size(25.dp),
                        contentDescription = "Key password"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                value = nik,
                onValueChange = { nik = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_reg3),
                        style = TextStyle(
                            color = colorResource(id = R.color.bg_splash),
                            fontSize = 16.sp
                        )
                    )
                }
            )


            OutlinedTextField(
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = colorResource(id = R.color.bg_splash),
                    unfocusedBorderColor = Color.Gray,
//                disabledIndicatorColor = Color.Transparent
                ),
//            draw
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.info_pes_date),
                        modifier = Modifier.size(25.dp),
                        contentDescription = "Key password"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                value = lahir,
                onValueChange = { lahir = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_reg5),
                        style = TextStyle(
                            color = colorResource(id = R.color.bg_splash),
                            fontSize = 16.sp
                        )
                    )
                }
            )


            OutlinedTextField(
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = colorResource(id = R.color.bg_splash),
                    unfocusedBorderColor = Color.Gray,
//                disabledIndicatorColor = Color.Transparent
                ),
//            draw
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.info_pes_loc),
                        modifier = Modifier.size(25.dp),
                        contentDescription = "Key password"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                value = alamat,
                onValueChange = { alamat = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_reg6),
                        style = TextStyle(
                            color = colorResource(id = R.color.bg_splash),
                            fontSize = 16.sp
                        )
                    )
                }
            )


            Spacer(modifier = Modifier.height(2.dp))

            OutlinedButton(onClick = {takePictureContract.launch()}, shape = CircleShape, border = BorderStroke(1.dp, Color.Gray),colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(
                id = R.color.bg_splash
            )), modifier = Modifier.fillMaxWidth()) {

                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row() {
                        Text(text = stringResource(id = R.string.label_reg7), style = TextStyle(color = colorResource(
                            id = R.color.bg_splash
                        ), fontSize = 16.sp))
                    }

                    Spacer(modifier = Modifier.width(64.dp))

                    Row() {
                        Button(onClick = {takePictureContract.launch()}, shape = CircleShape, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(
                            id = R.color.bg_splash
                        )), modifier = Modifier
                            .height(48.dp)
                            .width(108.dp)) {
                            Text(text = stringResource(id = R.string.button_label_reg), color = Color.White, style = TextStyle(fontSize = 16.sp))
                        }
                    }

                }


            }

            Spacer(modifier = Modifier.height(12.dp))




            Column(
                horizontalAlignment = Alignment.CenterHorizontally
//            modifier = Modifier
//                .fillMaxWidth()

//                .align(alignment = Alignment.CenterHorizontally)
            ) {
//                var auth = false
//                for (index in vm.userList) {
//                    if (index.username == usernameInput && index.password == passwordInput) {
//                        auth = true
//                        userId = index.userId.toString()
//                    }
//                }

                val newJknUser = JknUserModel(uid, firstname, lastname, nik, lahir, alamat)


                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bg_splash)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(58.dp),
//                .padding(25.dp),
//                    .width(12.dp)
                    onClick = {
                        val errorToast = Toast.makeText(lCOntext,"Failed to register", Toast.LENGTH_SHORT)
                        val addToFirebase = AddDataToFirebase()
                        addToFirebase.addJknDataToFirebase(
                            JknUserModel(uid, firstname, lastname, nik, lahir, alamat, ""),
                            { jknUserModel ->
                                if (jknUserModel!=null) {
                                    takenImage = BitmapFactory.decodeResource(lCOntext.resources, R.drawable.other_2).asImageBitmap()
                                    onSubmitActionEvent(takenImage, captionText)
                                    lCOntext.startActivity(
                                        Intent(lCOntext, HomeActivity::class.java)
                                            .putExtra("userId", userId)
                                    )
                                }
                                else {
                                    errorToast.show()
                                }
                            },
                            {
                                errorToast.show()
                            }
                        )
//                        Upload image nunggu Bima
//                        captionText = ""

//                        JknUserServiceBuilder.api.addJknUser(newJknUser).enqueue(object :
//                            Callback<JknUserModel> {
//                            override fun onResponse(
//                                call: Call<JknUserModel>,
//                                response: Response<JknUserModel>
//                            ) {
//                                val addedJknUser = response.body()
//                                Log.d("POST_SUCCESS", "User ${addedJknUser?.firstname} has been posted.")
//                                lCOntext.startActivity(
//                                    Intent(lCOntext, HomeActivity::class.java)
//                                        .putExtra("userId", userId)
//                                )
//                            }
//
//                            override fun onFailure(call: Call<JknUserModel>, t: Throwable) {
//                                Log.e("POST_FAILURE", "Error add user: ${t.message}")
//                            }
//                        })
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.label_regjkn),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))


            }


        }
    }
}




//
//@Preview(showBackground = true)
//@Composable
//fun LoginFormPreview() {
//    Project3activityTheme {
//        RegJKN(JknUserViewModel(), "", )
//    }
//}