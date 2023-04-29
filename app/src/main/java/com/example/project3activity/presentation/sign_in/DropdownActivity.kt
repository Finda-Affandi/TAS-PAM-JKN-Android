package com.example.project3activity.presentation.sign_in

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project3activity.presentation.sign_in.ui.theme.Project3activityTheme
import com.google.firebase.firestore.FirebaseFirestore

class DropdownActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val daysOfWeek = remember { mutableStateOf(emptyList<String>()) }
            FirebaseFirestore.getInstance()
                .collection("booking-data")
                .document("KDD7kjXbpHCekFlxYWcF")
                .get()
                .addOnSuccessListener { document ->
                    val daysField = document.getString("days") ?: ""
                    daysOfWeek.value = daysField.split(", ")
                }
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DaysButtons(daysOfWeek = daysOfWeek.value)
//                    FirestoreDropdownMenu()
                }
            }
        }
    }
}

@Composable
fun DaysButtons(daysOfWeek: List<String>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        daysOfWeek.forEach { day ->
            Button(onClick = { /* Handle button click */ }) {
                Text(text = day)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DaysButtons(daysOfWeek = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"))
}
