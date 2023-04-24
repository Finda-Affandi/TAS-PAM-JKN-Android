package com.example.project3activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.project3activity.Firebase.UpdateFirebaseData
import com.example.project3activity.models.*
import com.example.project3activity.ui.screens.RegJKN
import com.example.project3activity.ui.theme.Project3activityTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class RegJKNActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = JknUserViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userId = getIntent().getStringExtra("userId") ?: ""
                    RegJKN(vm, userId, onSubmitActionEvent = ::uploadImage)
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
        val userId = getIntent().getStringExtra("userId") ?: ""

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

                val currentUser = FirebaseAuth.getInstance().currentUser
                val userId = currentUser?.uid

                val updateData = UpdateFirebaseData()
                updateData.updateData(this, userId.toString(), "photoUrl", downloadUri.toString())
//                val newImage = ImageModel(userId.toInt(), downloadUri.toString())
//                ImageServiceBuilder.api.addImage(newImage).enqueue(object :
//                    Callback<ImageModel> {
//                    override fun onResponse(
//                        call: Call<ImageModel>,
//                        response: Response<ImageModel>
//                    ) {
//                        val addedJknUser = response.body()
//                        Log.d("POST_SUCCESS", "Image has been posted.")
//                    }
//
//                    override fun onFailure(call: Call<ImageModel>, t: Throwable) {
//                        Log.e("POST_FAILURE", "Error add user: ${t.message}")
//                    }
//                })
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
