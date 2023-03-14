package com.example.project3activity.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.repositories.JknUserGetRepository
import kotlinx.coroutines.launch

class JknUserViewModel : ViewModel() {
    private var _jknUserList = mutableStateListOf<JknUserModel>()

    var errorMessage: String by mutableStateOf("")
    val jknUserList: List<JknUserModel>
        get() = _jknUserList

    fun getJknUserList() {
        viewModelScope.launch {
            val apiClient = JknUserGetRepository.getClient()
            try {
                _jknUserList.clear()
                _jknUserList.addAll(apiClient.getJknUser())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}