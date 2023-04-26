package com.example.project3activity.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.project3activity.*
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.R
import com.example.project3activity.models.Constants
import com.example.project3activity.ui.BottomNavItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.project3activity.presentation.sign_in.GoogleAuthUiClient
import com.example.project3activity.ui.theme.Project3activityTheme
import com.google.android.gms.auth.api.identity.Identity


@Composable
fun Greeting(name: String) {
    Text(text = "Hi,",
        style = TextStyle(
            fontSize = 18.sp
        ),
        modifier = Modifier
            .padding(start = 19.dp, top = 83.dp))
    Text(text = "$name",
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        ),
        modifier = Modifier
            .padding(start = 18.dp, top = 104.dp))
}

//@Composable
//fun DotsIndicator(
//    totalDots: Int,
//    selectedIndex: Int
//) {
//
//    LazyRow(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
//    ) {
//
//        items(totalDots) { index ->
//            if (index == selectedIndex) {
//                Box(
//                    modifier = Modifier
//                        .size(12.dp)
//                        .clip(CircleShape)
//                        .background(color = Color(0xFF00CBA9))
//                )
//            } else {
//                Box(
//                    modifier = Modifier
//                        .size(10.dp)
//                        .clip(CircleShape)
//                        .background(color = Color(0xFFA1A1A1))
//                )
//            }
//
//            if (index != totalDots - 1) {
//                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
//            }
//        }
//    }
//}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Hero(viewModel: GetFirebaseData = viewModel()) {

    val items = listOf(
        BottomNavItems.Home,
        BottomNavItems.Article,
        BottomNavItems.Profile
    )

    val lCOntext = LocalContext.current

    val SSOdata = GoogleAuthUiClient(
        context = lCOntext,
        oneTapClient = Identity.getSignInClient(lCOntext)
    ).getSignedInUser()


    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid

//    Get user information
    val userData by viewModel.getUserData(userId!!)

    val jknUserData by viewModel.getJknPatientData(userId!!)

    var hasJkn: Boolean = false
    if (jknUserData != null) {
        hasJkn = true
    }

    val navController = rememberNavController()


    var username by remember {
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


    Box(modifier = Modifier.zIndex(3f)) {
        //Pattern
        Image(
            painter = painterResource(id = R.drawable.pattern),
            contentDescription = "pattern",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(width = 400.dp)
                .height(height = 85.dp)

        )


    }

    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    )
    {
        Column(modifier = Modifier.padding(start = 280.dp, top = 26.dp)) {
            //Avatar
            if (SSOdata != null) {
                if (SSOdata.profilePictureUrl != null) {
                    AsyncImage(
                        model = SSOdata.profilePictureUrl,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color.Gray, CircleShape)   // add a border (optional)
                            .clickable {
                                navController.navigate(BottomNavItems.Profile.screen_route)
                            },
                        contentScale = ContentScale.Crop
                    )
                }
                else{
                    Image(
                        painter = painterResource(R.drawable.other_2),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Fit,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)                       // clip to the circle shape
                            .border(3.dp, Color.Gray, CircleShape)   // add a border (optional)
                            .clickable {
                                navController.navigate(BottomNavItems.Profile.screen_route)
                            }
                    )

                }
            }
        }

        Column(modifier = Modifier.padding(start = 16.dp, top = 50.dp)) {
            if (SSOdata != null) {
                if (SSOdata.firstname != null) {
                    Text(
                        text = "Hello, ${SSOdata.firstname}",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .padding(top = 45.dp)
                    )
                }
                else{
                    Text(
                        text = "Hello, ${userData?.firstname}",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .padding(top = 45.dp)
                    )
                }

            }

        }

//    Column(modifier = Modifier.padding(start = 16.dp, top = 57.dp)) {
//
//        Text(
//            text = currentUser.email,
//            color = Color.Black,
//            style = TextStyle(
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            ),
//            modifier = Modifier
//                .padding(top = 45.dp)
//        )
//
//    }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 130.dp)
        ) {
            val imageList = Constants.imageList
            val pagerState = rememberPagerState()

            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 400.dp)) {
                HorizontalPager(
                    count = imageList.size,
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    PagerItemScreen(imageUrl = imageList[page])
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                        .padding(top = 5.dp),
                    activeColor = Color.Green,
                    inactiveColor = Color.LightGray
                )
            }
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 330.dp)
            )
            {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            lCOntext.startActivity(
                                Intent(lCOntext, AmbulanceActivity::class.java)
                                    .putExtra("userId", userId)
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffd0342c)),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .defaultMinSize(1.dp, minHeight = 1.dp)
                            .size(size = 107.dp)
                    )

                    {
                        Column(
                            modifier = Modifier
                                .height(200.dp)
                                .padding(horizontal = 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.ambulancce_icon_1),
                                contentDescription = "Ambulance-icon",
                                modifier = Modifier
                                    .width(width = 60.dp)
                                    .height(height = 57.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .height(2.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.label_icon1),
                                color = Color.White,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        }

                    }

                    Button(
                        onClick = {
                            lCOntext.startActivity(
                                Intent(lCOntext, ConsActivity::class.java)
                                    .putExtra("userId", userId)
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .defaultMinSize(1.dp, minHeight = 1.dp)
                            .size(size = 107.dp)
                    )

                    {
                        Column(

                            modifier = Modifier
                                .height(200.dp)
                                .padding(horizontal = 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.doctor_icon_1),
                                contentDescription = "doctor-icon",
                                modifier = Modifier
                                    .width(width = 60.dp)
                                    .height(height = 57.dp)
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(2.dp)
                            )


                            Text(
                                text = stringResource(id = R.string.label_icon2),
                                color = Color.Black,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        }

                    }


//                Button(
//                    onClick = {
//                        lCOntext.startActivity(
//                            Intent(lCOntext, FaskesLocActivity::class.java)
//                                .putExtra("userId", userId)
//                        )
//                    },
//                    shape = RoundedCornerShape(8.dp),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
//                    contentPadding = PaddingValues(),
//                    modifier = Modifier
//                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
//                        .defaultMinSize(1.dp, minHeight = 1.dp)
//                        .size(size = 107.dp)
//                )
//                {
//                    Column(
//
//                        modifier = Modifier
//                            .height(200.dp)
//                            .padding(horizontal = 0.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    )
//                    {
//                        Image(
//                            painter = painterResource(id = R.drawable.loc_icon),
//                            contentDescription = "Location-icon",
//                            modifier = Modifier
//                                .width(width = 60.dp)
//                                .height(height = 57.dp)
//                        )
//
//                        Spacer(
//                            modifier = Modifier
//                                .height(2.dp)
//                        )
//
//
//                        Text(
//                            text = stringResource(id = R.string.label_icon3),
//                            color = Color.Black,
//                            style = TextStyle(
//                                fontWeight = FontWeight.Bold
//                            ),
//                            textAlign = TextAlign.Center,
//                            fontSize = 16.sp
//                        )
//                    }
//
//                }

                    Button(
                        onClick = {

                            if (hasJkn) {
                                Toast.makeText(
                                    lCOntext,
                                    lCOntext.getResources().getString(R.string.Account_registered),
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                lCOntext.startActivity(
                                    Intent(lCOntext, RegJKNActivity::class.java)
                                        .putExtra("userId", userId)
                                )
                            }
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .defaultMinSize(1.dp, minHeight = 1.dp)
                            .size(size = 107.dp)
                    )
                    {
                        Column(

                            modifier = Modifier
                                .height(200.dp)
                                .padding(horizontal = 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.regjkn_icon),
                                contentDescription = "Registrasi-JKN-icon",
                                modifier = Modifier
                                    .width(width = 60.dp)
                                    .height(height = 57.dp)
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(2.dp)
                            )


                            Text(
                                text = stringResource(id = R.string.label_icon4),
                                color = Color.Black,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }


//Baris Kedua
            Column(
            )
            {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (hasJkn) {
                                lCOntext.startActivity(
                                    Intent(lCOntext, InfoActivity::class.java)
                                        .putExtra("userId", userId)
                                )
                            } else {
                                Toast.makeText(
                                    lCOntext,
                                    lCOntext.getResources().getString(R.string.Account_not_found),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .defaultMinSize(1.dp, minHeight = 1.dp)
                            .size(size = 107.dp)
                    )
                    {
                        Column(
                            modifier = Modifier
                                .height(200.dp)
                                .padding(horizontal = 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.info_icon),
                                contentDescription = "Informasi-Peserta-icon",
                                modifier = Modifier
                                    .width(width = 60.dp)
                                    .height(height = 57.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .height(1.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.label_icon5),
                                color = Color.Black,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                lineHeight = 14.sp
                            )
                        }

                    }

                    Button(
                        onClick = {
                            lCOntext.startActivity(
                                Intent(lCOntext, DaftarLayananActivity::class.java)
                                    .putExtra("userId", userId)
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .defaultMinSize(1.dp, minHeight = 1.dp)
                            .size(size = 107.dp)
                    )

                    {
                        Column(

                            modifier = Modifier
                                .height(200.dp)
                                .padding(horizontal = 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.reglay_icon),
                                contentDescription = "Daftar-Layanan-icon",
                                modifier = Modifier
                                    .width(width = 60.dp)
                                    .height(height = 57.dp)
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(2.dp)
                            )


                            Text(
                                text = stringResource(id = R.string.label_icon6),
                                color = Color.Black,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                lineHeight = 11.sp
                            )
                        }

                    }


                    Button(
                        onClick = {
                            lCOntext.startActivity(
                                Intent(lCOntext, InformasiKlinikActivity::class.java)
                                    .putExtra("userId", userId)
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        contentPadding = PaddingValues(),
                        modifier = Modifier

                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .defaultMinSize(1.dp, minHeight = 1.dp)
                            .size(size = 107.dp)
                    )

                    {
                        Column(

                            modifier = Modifier
                                .height(200.dp)
                                .padding(horizontal = 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.rumkit_icon),
                                contentDescription = "Informasi-Klinik-icon",
                                modifier = Modifier
                                    .width(width = 60.dp)
                                    .height(height = 57.dp)
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(1.dp)
                            )


                            Text(
                                text = stringResource(id = R.string.label_icon7),
                                color = Color.Black,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                lineHeight = 14.sp
                            )
                        }
                    }

//                Button(
//                    onClick = {
//                        lCOntext.startActivity(
//                            Intent(lCOntext, OtherActivity::class.java)
//                                .putExtra("userId", userId)
//                        )
//                    },
//                    shape = RoundedCornerShape(8.dp),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
//                    contentPadding = PaddingValues(),
//                    modifier = Modifier
//
//                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
//                        .defaultMinSize(1.dp, minHeight = 1.dp)
//                        .size(size = 80.dp)
//                )
//
//                {
//                    Column(
//
//                        modifier = Modifier
//                            .height(200.dp)
//                            .padding(horizontal = 0.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    )
//                    {
//                        Image(
//                            painter = painterResource(id = R.drawable.menu_icon),
//                            contentDescription = "Menu-Lainnya-icon",
//                            modifier = Modifier
//                                .width(width = 48.dp)
//                                .height(height = 45.dp)
//                        )
//
//                        Spacer(
//                            modifier = Modifier
//                                .height(2.dp)
//                        )
//                        Text(
//                            text = stringResource(id = R.string.label_icon8),
//                            color = Color.Black,
//                            style = TextStyle(
//                                fontWeight = FontWeight.Medium
//                            ),
//                            textAlign = TextAlign.Center,
//                            fontSize = 12.sp,
//                            lineHeight = 11.sp
//                        )
//                    }
//                }
                }
            }
//        Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.History),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )
            )
        }




        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
//            .fillMaxWidth()
                .padding(start = 16.dp, top = 595.dp, end = 16.dp)

        ) {


//        Box(modifier = Modifier.background(Image(asset = ImageAsset)))

            Button(
                onClick = {
                    lCOntext.startActivity(
                        Intent(lCOntext, RecentActivity::class.java)
                            .putExtra("index", "1")
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(108.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                )
            )
            {

                Row(
                    modifier = Modifier
                        .width(275.dp)
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = stringResource(id = R.string.History_Button_2_dr),
                            color = Color.White,
                            textAlign = TextAlign.Left,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp)
                        )

                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            modifier = Modifier
                                .padding(top = 2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.dr_icon_recent),
                                contentDescription = "Vector", tint = Color.White
                            )

                            Text(
                                text = stringResource(id = R.string.Spec_2),
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 6.dp, top = 3.dp)

                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Row(
                            modifier = Modifier
                                .padding(top = 2.dp, start = 3.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.location_icon_recent),
                                contentDescription = "Location", tint = Color.White
                            )

                            Text(
                                text = stringResource(id = R.string.Loc_2),
                                color = Color.White,
//                    lineHeight = 95.sp,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 6.dp)
//                            .fillMaxWidth()

                            )
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            modifier = Modifier
                                .padding(top = 2.dp, start = 2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.time_icon_recent),
                                contentDescription = "Time", tint = Color.White
                            )

                            Text(
                                text = stringResource(id = R.string.WorkDay_2),
                                color = Color.White,
//                    lineHeight = 95.sp,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 5.dp)
//                            .fillMaxWidth()

                            )
                        }

                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.dr_2),
                    contentDescription = "Character-icon",
                    modifier = Modifier
                        .size(250.dp)
                )
            }

            Spacer(modifier = Modifier.height(0.dp))

            Button(
                onClick = {
                    lCOntext.startActivity(
                        Intent(lCOntext, RecentActivity::class.java)
                            .putExtra("index", "2")
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(108.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                )
            )
            {

                Row(
                    modifier = Modifier
                        .width(275.dp)
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = stringResource(id = R.string.dr_3),
                            color = Color.White,
                            textAlign = TextAlign.Left,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp)
                        )

                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            modifier = Modifier
                                .padding(top = 2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.dr_icon_recent),
                                contentDescription = "Vector", tint = Color.White
                            )

                            Text(
                                text = stringResource(id = R.string.Spec_3),
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 6.dp, top = 3.dp)

                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Row(
                            modifier = Modifier
                                .padding(top = 2.dp, start = 3.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.location_icon_recent),
                                contentDescription = "Location", tint = Color.White
                            )

                            Text(
                                text = stringResource(id = R.string.Loc_3),
                                color = Color.White,
//                    lineHeight = 95.sp,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 6.dp)
//                            .fillMaxWidth()

                            )
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            modifier = Modifier
                                .padding(top = 2.dp, start = 2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.time_icon_recent),
                                contentDescription = "Time", tint = Color.White
                            )

                            Text(
                                text = stringResource(id = R.string.WorkDay_3),
                                color = Color.White,
//                    lineHeight = 95.sp,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 5.dp)
//                            .fillMaxWidth()

                            )
                        }

                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.character_2),
                    contentDescription = "Character-icon",
                    modifier = Modifier
                        .size(250.dp)
                )
            }

            Spacer(modifier = Modifier.height(0.dp))

            Button(
                onClick = {
                    lCOntext.startActivity(
                        Intent(lCOntext, RecentActivity::class.java)
                            .putExtra("index", "3")
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(108.dp),

                elevation = ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                )
            )
            {

                Row(
                    modifier = Modifier
                        .width(275.dp)
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = stringResource(id = R.string.dr_4),
                            color = Color.White,
                            textAlign = TextAlign.Left,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp)
                        )

                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            modifier = Modifier
                                .padding(top = 2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.dr_icon_recent),
                                contentDescription = "Vector", tint = Color.White
                            )

                            Text(
                                text = stringResource(id = R.string.Spec_4),
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 6.dp, top = 3.dp)

                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Row(
                            modifier = Modifier
                                .padding(top = 2.dp, start = 3.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.location_icon_recent),
                                contentDescription = "Location", tint = Color.White
                            )

                            Text(
                                text = stringResource(id = R.string.Loc_4),
                                color = Color.White,
//                    lineHeight = 95.sp,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 6.dp)
//                            .fillMaxWidth()

                            )
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            modifier = Modifier
                                .padding(top = 2.dp, start = 2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.time_icon_recent),
                                contentDescription = "Time", tint = Color.White
                            )

                            Text(
                                text = stringResource(id = R.string.WorkDay_4),
                                color = Color.White,
//                    lineHeight = 95.sp,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 5.dp)
//                            .fillMaxWidth()

                            )
                        }

                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.dr_3),
                    contentDescription = "Character-icon",
                    modifier = Modifier
                        .size(250.dp)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))


        }
    }
}





//@Composable
//fun BottomNavbar(){
//
//    var bottomState by remember {
//        mutableStateOf("Home")
//    }
//
//
//    Scaffold(
//        content = {
//            Greeting(name = "admin")
//            Hero()
//        },
//        bottomBar = {
//            BottomNavigation(
//                backgroundColor = Color.White,
//                contentColor = Color(0xFF868686),
////                modifier = Modifier
////                    .padding(horizontal = 20.dp)
//            ) {
//                BottomNavigationItem(
//                    selected = bottomState == "Home",
//                    onClick = { bottomState == "Home"},
//                    label = { Text(text = stringResource(id = R.string.Nav_Home), color = Color(0xFF4ECB71)) },
//                    icon = { Icon(painter = painterResource(id = R.drawable.navbar_home), contentDescription = null, modifier = Modifier.size(22.dp), tint = Color(0xFF4ECB71)) }
//                )
//                BottomNavigationItem(
//                    selected = bottomState == "Lokasi",
//                    onClick = { bottomState == "Lokasi"},
//                    label = { Text(text = stringResource(id = R.string.Nav_Loc)) },
//                    icon = { Icon(painter = painterResource(id = R.drawable.navbar_lokasi), contentDescription = null, modifier = Modifier.size(22.dp)) }
//                )
//                FloatingActionButton(
//                    onClick = { bottomState == "Janji Temu"},
//                    backgroundColor = Color(0xFF4ECB71 )
//                ){
//                    Icon(painter = painterResource(id = R.drawable.navbar_janji_temu), contentDescription = null, modifier = Modifier
//                        .size(20.dp)
//                        .offset(y = -13.dp), tint = Color.White)
//                    Text(text = stringResource(id = R.string.Nav_Res), style = TextStyle(fontSize = 11.sp), lineHeight = 10.sp, color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.offset(y = 8.dp))
//                }
//                BottomNavigationItem(
//                    selected = bottomState == "FAQ",
//                    onClick = { bottomState == "FAQ"},
//                    label = { Text(text = stringResource(id = R.string.Nav_FAQ)) },
//                    icon = { Icon(painter = painterResource(id = R.drawable.navbar_faq), contentDescription = null, modifier = Modifier.size(22.dp)) }
//                )
//                BottomNavigationItem(
//                    selected = bottomState == "Kartu",
//                    onClick = { bottomState == "Kartu"},
//                    icon = { Icon(painter = painterResource(id = R.drawable.navbar_kartu), contentDescription = null, modifier = Modifier.size(22.dp)) }
//                )
//            }
//        }
//    )
//}
//
//
//
//@Composable
//fun centerbutton(scope: CoroutineScope,
//                 scaffoldState: ScaffoldState
//){
//    FloatingActionButton(onClick = { scope.launch { scaffoldState.snackbarHostState.showSnackbar("Janji Temu", actionLabel = "Janji Temu", duration = SnackbarDuration.Indefinite) } }) {
//
//    }
//
//}




//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Project3activityTheme {
////        Greeting("admin")
//        Hero(viewModel: GetFirebaseData = viewModel())
////        BottomNavbar()
//    }
//}