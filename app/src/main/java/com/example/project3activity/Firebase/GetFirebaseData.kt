package com.example.project3activity.Firebase

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.models.JknUserModel
import com.example.project3activity.models.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

//data class ArticleDataResult(
//    var description: String = "",
//    var link: String = "",
//    var pubDate: String = "",
//    var thumbnail: String = "",
//    var title: String = ""
//)
//
//data class ArticleDataResultId(
//    var id: String = "",
//    var description: String = "",
//    var link: String = "",
//    var pubDate: String = "",
//    var thumbnail: String = "",
//    var title: String = ""
//)

data class ArticleDataResult(
    var author: String = "",
    var publishedAt: String = "",
    var text: String = "",
    var title: String = ""
)

data class ArticleDataResultId(
    var id: String = "",
    var author: String = "",
    var publishedAt: String = "",
    var text: String = "",
    var title: String = ""
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
    fun getArticleData(documentId: String): MutableState<ArticleDataResult?> {
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

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun fetchArticleData(): LiveData<List<ArticleDataResultId>> {
        var data = MutableLiveData<List<ArticleDataResultId>>()

        firestore.collection("articles")
            .get()
            .addOnSuccessListener { querySnapshot ->
                var results = mutableListOf<ArticleDataResultId>()

                for (document in querySnapshot.documents) {
                    var result = document.toObject(ArticleDataResult::class.java)
                    if (result != null) {
                        val res = ArticleDataResultId()
                        res.id = document.id
                        res.author = result.author
                        println(result.text)
                        res.publishedAt = result.publishedAt
                        res.text = result.text
                        res.title = result.title
                        results.add(res)
                    }
                }
                data.value = results
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting data", exception)
            }

        return data
    }
}