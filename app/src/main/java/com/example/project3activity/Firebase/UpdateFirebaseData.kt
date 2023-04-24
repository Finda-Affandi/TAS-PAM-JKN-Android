package com.example.project3activity.Firebase

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class UpdateFirebaseData {
    fun updateData(lContext: Context ,documentId: String, field: String, newValue: String) {
        var firestore = FirebaseFirestore.getInstance()
        firestore.collection("JknPatient")
            .document(documentId)
            .update(field, newValue)
            .addOnSuccessListener {
                println("Succes update data")
            }
            .addOnFailureListener {
                Toast.makeText(lContext, "Error add url to firebase!", Toast.LENGTH_SHORT).show()
            }
    }
}