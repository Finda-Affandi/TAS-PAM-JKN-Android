package com.example.project3activity.Firebase

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.models.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

data class UserResult(
    var firstname: String = "",
    var lastname: String = ""
)

class GetFirebaseData : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun getUserData(documentId: String): MutableState<UserModel?> {
        val data = remember {
            mutableStateOf<UserModel?>(null)
        }

        viewModelScope.launch {
            firestore.collection("users")
                .document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val result = documentSnapshot.toObject(UserModel::class.java)
                    if (result != null) {
                        data.value = result
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting data", exception)
                }
        }
        return data
    }
}