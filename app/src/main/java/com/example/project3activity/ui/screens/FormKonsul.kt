package com.example.project3activity.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project3activity.Firebase.GetFirebaseData

@Composable
fun DoctorConsultationDetails(viewModel: GetFirebaseData, DoctorId: String) {
    val DoctorData by viewModel.getConsulData(DoctorId!!)

    Column() {
        Text(text = " ${DoctorData?.name}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = " ${DoctorData?.location}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = " ${DoctorData?.speciality}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = " ${DoctorData?.workday}")
    }


}