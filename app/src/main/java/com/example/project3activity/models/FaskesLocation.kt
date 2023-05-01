package com.example.project3activity.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.repositories.JSONPlaceholderTypicode
import kotlinx.coroutines.launch

class FaskesLocation: ViewModel() {
    private var FaskesLocationList = mutableStateListOf<FaskesLocModel>()

    var errorMessage : String by mutableStateOf("")
    val FaskesLocList: List<FaskesLocModel>
        get() = FaskesLocationList

    fun getFaskesLocList() {
        viewModelScope.launch {
            val apiClient = JSONPlaceholderTypicode.getClient()
            try{
                FaskesLocationList.clear()
                FaskesLocationList.addAll(apiClient.getFaskesLoc())
            }
            catch (e: Exception){
                errorMessage = e.message!!
            }
        }
    }
}