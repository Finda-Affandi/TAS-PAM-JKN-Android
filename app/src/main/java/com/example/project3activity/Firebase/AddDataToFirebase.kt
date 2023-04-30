package com.example.project3activity.Firebase

import com.example.project3activity.models.ConsulModel
import com.example.project3activity.models.JknUserModel
import com.example.project3activity.models.UserModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class AddDataToFirebase {
    fun addUserDataToFirebase(
        userModel: UserModel,
        onSuccess: (userModel: UserModel?) -> Unit,
        onFailure: (exception: Exception) -> Unit
    ) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbCourses: CollectionReference = db.collection("users")

        dbCourses.document(userModel.uid).set(mapOf(
            "firstname" to userModel.firstname,
            "lastname" to userModel.lastname
        )).addOnSuccessListener {
            dbCourses.document(userModel.uid).get().addOnSuccessListener {
                if (it.exists()) {
                    onSuccess(it.toObject(UserModel::class.java))
                } else {
                    onSuccess(null)
                }
            }.addOnFailureListener(onFailure)
        }.addOnFailureListener(onFailure)
    }

    fun addJknDataToFirebase(
        jknUserModel: JknUserModel,
        onSuccess: (jknUserModel: JknUserModel?) -> Unit,
        onFailure: (exception: Exception) -> Unit
    ) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbCourses: CollectionReference = db.collection("JknPatient")

        dbCourses.document(jknUserModel.uid).set(mapOf(
            "firstname" to jknUserModel.firstname,
            "lastname" to jknUserModel.lastname,
            "nik" to jknUserModel.nik,
            "lahir" to jknUserModel.lahir,
            "alamat" to jknUserModel.alamat,
            "photoUrl" to jknUserModel.photoUrl,
        )).addOnSuccessListener {
            dbCourses.document(jknUserModel.uid).get().addOnSuccessListener {
                if (it.exists()) {
                    onSuccess(it.toObject(JknUserModel::class.java))
                } else {
                    onSuccess(null)
                }
            }.addOnFailureListener(onFailure)
        }.addOnFailureListener(onFailure)
    }

    fun addConsulDataToFirebase(
        consulModel: ConsulModel,
        onSuccess: (consulModel: ConsulModel?) -> Unit,
        onFailure: (exception: Exception) -> Unit
    ) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbCourses: CollectionReference = db.collection("consul")

        dbCourses.document(consulModel.docId).set(mapOf(
            "userId" to consulModel.userId,
            "doctor" to consulModel.doctor,
            "speciality" to consulModel.speciality,
            "location" to consulModel.location,
            "date" to consulModel.date,
            "time" to consulModel.time,
        )).addOnSuccessListener {
            dbCourses.document(consulModel.docId).get().addOnSuccessListener {
                if (it.exists()) {
                    onSuccess(it.toObject(ConsulModel::class.java))
                } else {
                    onSuccess(null)
                }
            }.addOnFailureListener(onFailure)
        }.addOnFailureListener(onFailure)
    }
}