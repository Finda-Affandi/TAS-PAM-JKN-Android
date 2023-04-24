package com.example.project3activity.ui.screens


import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext



@Composable
fun ObatDaftarLayananScreen() {
    val context = LocalContext.current
    val downloadLink = "https://bpjs-kesehatan.go.id/bpjs/dmdocuments/a85e064985b713815222f9a4ca213f94.pdf"

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode != Activity.RESULT_OK) {
            Toast.makeText(
                context,
                "Unduh gagal",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(Modifier.padding(16.dp)) {
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, downloadLink.toUri())
                launcher.launch(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Unduh PDF")
        }
    }
}