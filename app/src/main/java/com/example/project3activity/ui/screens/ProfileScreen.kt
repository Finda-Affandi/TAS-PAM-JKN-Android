package com.example.project3activity.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project3activity.FaskesLocActivity
import com.example.project3activity.InfoActivity
import com.example.project3activity.InformasiKlinikActivity
import com.example.project3activity.R
import com.example.project3activity.models.JknUserViewModel
import com.example.project3activity.models.UserViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileScreen(vm : UserViewModel, vj : JknUserViewModel, userId : String ){

    var hasJkn : Boolean = false

    val lContext = LocalContext.current
    val lCOntext = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }

    var firstname by remember {
        mutableStateOf("")
    }

    var lastname by remember {
        mutableStateOf("")
    }

//    LaunchedEffect(
//        Unit,
//        block = {
//            vm.getUserList()
//        }
//    )
//
//    for (index in vm.userList) {
//        if (index.userId.toString() == userId) {
//            username = index.username
//            firstname = index.firstname
//            lastname = index.lastname
//        }
//    }
//
//    LaunchedEffect(
//        Unit,
//        block = {
//            vm.getUserList()
//        }
//    )
//
//    for (index in vm.userList) {
//        if (index.userId.toString() == userId) {
//            username = index.username
//        }
//    }
//
//    LaunchedEffect(
//        Unit,
//        block = {
//            vj.getJknUserList()
//        }
//    )
//
//    for (index in vj.jknUserList) {
//        if (index.id.toString() == userId) {
//            hasJkn = true
//        }
//    }


    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(colorResource(id = R.color.purple_700))
//            .wrapContentSize(Alignment.Center)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 48.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.other_2),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Fit,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(3.dp, Color.Gray, CircleShape)   // add a border (optional)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier
                    .align(Alignment.CenterVertically)) {
                    Text(text = username,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    )

                    Text(text = firstname + " " + lastname,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Thin
                    ))

                }

            }


            Spacer(modifier = Modifier.height(16.dp))

            Divider(modifier = Modifier.height(1.dp), color = Color.Gray)

            Button(
                onClick = {
                    Toast.makeText(lContext, lContext.getResources().getString(R.string.under_developing), Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffffffff)),

                ) {
                Row(modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                    .fillMaxWidth())
                {
                    Image(painter = painterResource(id = R.drawable.doctor_icon_1), contentDescription = "doctor profile", modifier = Modifier.size(28.dp))

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                            .weight(1f),
                        text = stringResource(id = R.string.profile_1),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ))

                    Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "Arrow Right", modifier = Modifier.align(Alignment.CenterVertically) )
                }
            }

            Divider(modifier = Modifier.height(1.dp), color = Color.Gray)

            Button(
                onClick = {
                    if (hasJkn) {
                        lCOntext.startActivity(
                            Intent(lCOntext, InfoActivity::class.java)
                                .putExtra("userId", userId)
                        )
                    }
                    else {
                        Toast.makeText(lCOntext, lCOntext.getResources().getString(R.string.Account_not_found), Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffffffff)),

                ) {
                Row(modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                    .fillMaxWidth())
                {
                    Image(painter = painterResource(id = R.drawable.info_icon), contentDescription = "info profile", modifier = Modifier.size(28.dp))

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                            .weight(1f),
                        text = stringResource(id = R.string.profile_2),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ))

                    Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "Arrow Right", modifier = Modifier.align(Alignment.CenterVertically) )
                }
            }

            Divider(modifier = Modifier.height(1.dp), color = Color.Gray)

            Button(
                onClick = {
                    lCOntext.startActivity(
                        Intent(lCOntext, InformasiKlinikActivity::class.java)
                            .putExtra("userId", userId)
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffffffff)),

                ) {
                Row(modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                    .fillMaxWidth())
                {
                    Image(painter = painterResource(id = R.drawable.rumkit_icon), contentDescription = "clinic info profile", modifier = Modifier.size(28.dp))

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                            .weight(1f),
                        text = stringResource(id = R.string.profile_3),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ))

                    Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "Arrow Right", modifier = Modifier.align(Alignment.CenterVertically) )
                }
            }

            Divider(modifier = Modifier.height(1.dp), color = Color.Gray)

            Button(
                onClick = {
                    lCOntext.startActivity(
                        Intent(lCOntext, FaskesLocActivity::class.java)
                            .putExtra("userId", userId)
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffffffff)),

                ) {
                Row(modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                    .fillMaxWidth())
                {
                    Image(painter = painterResource(id = R.drawable.loc_icon), contentDescription = "doctor profile", modifier = Modifier.size(28.dp))

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                            .weight(1f),
                        text = stringResource(id = R.string.profile_4),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ))

                    Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "Arrow Right", modifier = Modifier.align(Alignment.CenterVertically) )
                }
            }



            Divider(modifier = Modifier.height(1.dp), color = Color.Gray)


        }
//        Text(
//            text = "Profile Screen",
//            fontWeight = FontWeight.Bold,
//            color = Color.White,
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            fontSize = 20.sp
//        )

    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewProfile() {
//    Project3activityTheme {
////        Greeting2("Android")
//        ProfileScreen()
//    }
//
//}