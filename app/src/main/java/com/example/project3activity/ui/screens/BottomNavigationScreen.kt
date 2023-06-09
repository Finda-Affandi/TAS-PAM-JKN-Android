package com.example.project3activity.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.R
import com.example.project3activity.ui.BottomNavItems


@Composable
fun NavigationGraph(
    navController: NavHostController,
    getData : GetFirebaseData,
    userId: String,
    dest: String
) {
    var destination by remember {
        mutableStateOf("")
    }

    if (dest == "home") {
        destination = BottomNavItems.Home.screen_route
    }
    else if (dest == "profile") {
        destination = BottomNavItems.Profile.screen_route
    }


    NavHost(
        navController = navController,
        startDestination = destination
    ) {
        composable(BottomNavItems.Home.screen_route) {
            Hero(getData)
        }
        composable(BottomNavItems.Article.screen_route) {
            ArticleScreen(getData)
        }
        composable(BottomNavItems.Profile.screen_route) {
            ProfileScreen(getData)
        }
    }
}

@Composable
fun BottomNavigation(
    navController: NavController
){
    val items = listOf(
        BottomNavItems.Home,
        BottomNavItems.Article,
        BottomNavItems.Profile
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor =  Color.White,
        contentColor = colorResource(id = R.color.bg_splash)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = "${item.title} icon", modifier = Modifier.size(20.dp))

//                    Icon(
//                    imageVector = item.icon,
//                    contentDescription = "${item.title} icon")
//                    tint = colorResource(id = R.color.bg_splash)

//                    Icon(imageVector = ImageVector.vectorResource(id = item.icon), contentDescription = "${item.title} icon")

                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 11.sp,
//                        color = colorResource(id = R.color.bg_splash)
                        )
                },
                selectedContentColor = colorResource(id = R.color.bg_splash),
                unselectedContentColor = Color.LightGray,
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigationMainScreenView(getData : GetFirebaseData, userId: String, dest: String){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navController = navController,
            )
        }
    ) {
        NavigationGraph(navController = navController, getData, userId, dest)
    }
}