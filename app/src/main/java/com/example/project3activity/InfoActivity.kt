package com.example.project3activity

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.ui.screens.InfoPeserta
import com.example.project3activity.ui.theme.Project3activityTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val getData = GetFirebaseData()
        super.onCreate(savedInstanceState)
        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userId = getIntent().getStringExtra("userId") ?: ""
                    val navBarState = getIntent().getStringExtra("navBarState") ?: ""

                    InfoPeserta(getData, onSubmitActionEvent = ::uploadImage, navBarState)
                }
            }
        }
    }

    private fun uploadImage(img: ImageBitmap, caption: String){
        val fStorage = Firebase.storage
        val storageRef = fStorage.reference

        val fileName = SimpleDateFormat("yyyMMddHHmm'.png'").format(Date())
        val ref = storageRef.child("images/$fileName")

        val stream = ByteArrayOutputStream()
        img.asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream)
        val image = stream.toByteArray()

        var uploadTask = ref.putBytes(image)

        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                addData(downloadUri.toString(), caption)
            } else {
                Toast.makeText(applicationContext, "Failed Upload Image", Toast.LENGTH_LONG).show()
                // Handle failures
                // ...
            }
        }
    }

    private fun addData(imgUrl: String, caption: String){
        val fFireStore = Firebase.firestore

        val data = hashMapOf(
            "caption" to caption,
            "imgUrl" to imgUrl
        )

        fFireStore.collection("images")
            .add(data)
            .addOnSuccessListener { documentRefrence ->
                Toast.makeText(applicationContext, "Added data successfuly", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{e ->
                Toast.makeText(applicationContext, "Failed add data", Toast.LENGTH_LONG).show()

            }
    }


}


