package com.example.tbc_incomplete_service_davaleba13.view_models

import androidx.lifecycle.ViewModel
import com.example.tbc_incomplete_service_davaleba13.models.Field

class HomeFragmentViewModel : ViewModel() {

     var data = mutableListOf<List<Field>>()

    fun setMyData(data: List<List<Field>>){
        this.data = data.toMutableList()
    }
}