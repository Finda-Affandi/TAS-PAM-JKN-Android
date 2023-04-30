package com.example.project3activity.Firebase

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.FormKonsulActivity
import com.example.project3activity.models.ConsulModel
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
    var title: String = "",
    var image: String = ""
)

data class ArticleDataResultId(
    var id: String = "",
    var author: String = "",
    var publishedAt: String = "",
    var text: String = "",
    var title: String = "",
    var image: String = ""
)


data class DoctorConsultation(
    var id: String= "",
    var name: String = "",
    var location: String = "",
    var speciality: String = "",
    var workday: String = "",
    var img: String = ""
)

data class DoctorConsultationResult(
    var name: String = "",
    var location: String = "",
    var speciality: String = "",
    var workday: String = "",
    var img: String =  ""
)

data class ConsultResult(
    var date: String = "",
    var doctor: String = "",
    var location: String = "",
    var speciality: String = "",
    var time: String =  "",
    var userId: String =  ""
)

data class Consult(
    var id: String = "",
    var date: String = "",
    var doctor: String = "",
    var location: String = "",
    var speciality: String = "",
    var time: String =  "",
    var userId: String =  ""
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
                        res.image = result.image
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


    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun getConsulData(documentId: String): MutableState<DoctorConsultationResult?> {
        val data = remember {
            mutableStateOf<DoctorConsultationResult?>(null)
        }

        viewModelScope.launch {
            firestore.collection("dr-consult")
                .document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val result = documentSnapshot.toObject(DoctorConsultationResult::class.java)
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
    fun fetchDoctorConsultationData(): LiveData<List<DoctorConsultation>> {
        var consuldata = MutableLiveData<List<DoctorConsultation>>()

        firestore.collection("dr-consult")
            .get()
            .addOnSuccessListener { querySnapshot ->
                var drresults = mutableListOf<DoctorConsultation>()

                for (document in querySnapshot.documents) {
                    var drresult = document.toObject(DoctorConsultationResult::class.java)
                    if (drresult != null) {
                        val res = DoctorConsultation()
                        res.id = document.id
                        res.name = drresult.name
                        res.img = drresult.img
                        println(drresult.name)
                        res.location = drresult.location
                        res.speciality = drresult.speciality
                        res.workday = drresult.workday
                        drresults.add(res)
                    }
                }
                consuldata.value = drresults
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting data", exception)
            }

        return consuldata
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun fetchRequestedConsultationData(): LiveData<List<Consult>> {
        var patientconsuldata = MutableLiveData<List<Consult>>()

        firestore.collection("consul")
            .get()
            .addOnSuccessListener { querySnapshot ->
                var consulresult = mutableListOf<Consult>()

                for (document in querySnapshot.documents) {
                    var conresult = document.toObject(ConsultResult::class.java)
                    if (conresult != null) {
                        val res = Consult()
                        res.id = document.id
                        res.date = conresult.date
                        res.doctor = conresult.doctor
                        println(conresult.date)
                        res.location = conresult.location
                        res.speciality = conresult.speciality
                        res.time = conresult.time
                        res.userId = conresult.userId
                        consulresult.add(res)
                    }
                }
                patientconsuldata.value = consulresult
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting data", exception)
            }

        return patientconsuldata
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun getRequestedConsul(documentId: String): MutableState<ConsultResult?> {
        val data = remember {
            mutableStateOf<ConsultResult?>(null)
        }

        viewModelScope.launch {
            firestore.collection("consul")
                .document(documentId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val consultationresult = documentSnapshot.toObject(ConsultResult::class.java)
                    if (consultationresult != null) {
                        data.value = consultationresult
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting data", exception)
                }
        }
        return data
    }

}