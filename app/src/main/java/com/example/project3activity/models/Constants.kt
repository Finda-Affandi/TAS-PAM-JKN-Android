package com.example.project3activity.models

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object Constants {

    val imageList = mutableListOf<String>()
    // inisialisasi Firebase dan Firestore

    // fungsi untuk mengambil data dari Firestore
    private fun fetchImagesFromFirestore() {
        val firestore = Firebase.firestore
        firestore.collection("carouselimg")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // tambahkan URL gambar ke dalam imageList
                    imageList.add(document.getString("imgUrl")!!)
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore", "Error getting images", exception)
            }
    }

    // panggil fungsi fetchImagesFromFirestore saat aplikasi dijalankan
    init {
        fetchImagesFromFirestore()
    }
}