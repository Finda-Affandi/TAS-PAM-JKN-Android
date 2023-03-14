package com.example.project3activity.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.project3activity.R


sealed class BottomNavItems(
        var title: String,
        var icon: Int,
        val screen_route: String
){
//        object Home: BottomNavItems("Home", Icons.Rounded.Home, "home")
//        object Article: BottomNavItems("Article", Icons.Rounded.Info, "article")
//        object Profile: BottomNavItems("Profile", Icons.Rounded.Person, "profile")

        object Home: BottomNavItems("Home", R.drawable.navbar_home, "home")
        object Article: BottomNavItems("Article", R.drawable.navbar_article, "article")
        object Profile: BottomNavItems("Profile", R.drawable.navbar_person, "profile")

}