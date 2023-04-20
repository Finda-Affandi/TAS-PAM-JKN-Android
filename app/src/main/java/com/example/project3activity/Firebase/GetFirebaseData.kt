package com.example.project3activity.Firebase

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.models.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

data class DataResult(
    var firstname: String = "",
    var lastname: String = ""
)

class GetFirebaseData : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun fetchData(documentId: String): MutableState<DataResult?> {
        val data = remember {
            mutableStateOf<DataResult?>(null)
        }

        viewModelScope.launch {
            firestore.collection("users")
                .document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val result = documentSnapshot.toObject(DataResult::class.java)
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