package com.example.project3activity.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.repositories.FaskesGetRepository
import kotlinx.coroutines.launch

class FaskesViewModel : ViewModel() {
    private var _faskesList = mutableStateListOf<FaskesModel>()

    var errorMessage: String by mutableStateOf("")
    val faskesList: List<FaskesModel>
        get() = _faskesList

    fun getFaskesList() {
        viewModelScope.launch {
            val apiClient = FaskesGetRepository.getClient()
            try {
                _faskesList.clear()
                _faskesList.addAll(apiClient.getFaskes())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}