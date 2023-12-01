package com.example.tbc_incomplete_service_davaleba13.view_models

import androidx.lifecycle.ViewModel

class RegisteredFragmentViewModel : ViewModel() {

    var fieldTexts = mapOf<Int, String>()

    fun saveValues(fieldTexts: Map<Int, String>){
        this.fieldTexts = fieldTexts
    }
}