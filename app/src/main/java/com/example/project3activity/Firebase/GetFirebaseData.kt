package com.example.project3activity.Firebase

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.models.JknUserModel
import com.example.project3activity.models.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

data class ArticleDataResult(
    var author: String = "",
    var publishedAt: String = "",
    var text: String = ""
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

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun getJknPatientData(documentId: String): MutableState<JknUserModel?> {
        val data = remember {
            mutableStateOf<JknUserModel?>(null)
        }

        viewModelScope.launch {
            firestore.collection("JknPatient")
                .document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val result = documentSnapshot.toObject(JknUserModel::class.java)
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

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun fetchArticleData(documentId: String): MutableState<ArticleDataResult?> {
        val data = remember {
            mutableStateOf<ArticleDataResult?>(null)
        }

        viewModelScope.launch {
            firestore.collection("articles")
                .document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val result = documentSnapshot.toObject(ArticleDataResult::class.java)
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