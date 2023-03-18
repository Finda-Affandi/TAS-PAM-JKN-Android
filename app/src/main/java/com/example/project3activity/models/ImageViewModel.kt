package com.example.project3activity.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.repositories.ImageGetRepository
import com.example.project3activity.repositories.JknUserGetRepository
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {
    private var _imageList = mutableStateListOf<ImageModel>()

    var errorMessage: String by mutableStateOf("")
    val imageList: List<ImageModel>
        get() = _imageList

    fun getImageList() {
        viewModelScope.launch {
            val apiClient = ImageGetRepository.getClient()
            try {
                _imageList.clear()
                _imageList.addAll(apiClient.getImage())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}