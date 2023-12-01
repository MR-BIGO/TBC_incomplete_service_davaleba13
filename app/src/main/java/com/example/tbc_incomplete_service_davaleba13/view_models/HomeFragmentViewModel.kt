package com.example.tbc_incomplete_service_davaleba13.view_models

import androidx.lifecycle.ViewModel
import com.example.tbc_incomplete_service_davaleba13.models.Field
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeFragmentViewModel : ViewModel() {

    var data = mutableListOf<List<Field>>()
    var emptyRequiredField = ""

    init {
        val json = "[\n" +
                "  [\n" +
                "    {\n" +
                "      \"field_id\": 1,\n" +
                "      \"hint\": \"UserName\",\n" +
                "      \"field_type\": \"input\",\n" +
                "      \"keyboard\": \"text\",\n" +
                "      \"required\": false,\n" +
                "      \"is_active\": true,\n" +
                "      \"icon\": \"https://jemala.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"field_id\": 2,\n" +
                "      \"hint\": \"Email\",\n" +
                "      \"field_type\": \"input\",\n" +
                "      \"required\": true,\n" +
                "      \"keyboard\": \"text\",\n" +
                "      \"is_active\": true,\n" +
                "      \"icon\": \"https://jemala.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"field_id\": 3,\n" +
                "      \"hint\": \"phone\",\n" +
                "      \"field_type\": \"input\",\n" +
                "      \"required\": true,\n" +
                "      \"keyboard\": \"number\",\n" +
                "      \"is_active\": true,\n" +
                "      \"icon\": \"https://jemala.png\"\n" +
                "    }\n" +
                "  ],\n" +
                "  [\n" +
                "    {\n" +
                "      \"field_id\": 4,\n" +
                "      \"hint\": \"FullName\",\n" +
                "      \"field_type\": \"input\",\n" +
                "      \"keyboard\": \"text\",\n" +
                "      \"required\": true,\n" +
                "      \"is_active\": true,\n" +
                "      \"icon\": \"https://jemala.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"field_id\": 14,\n" +
                "      \"hint\": \"Jemali\",\n" +
                "      \"field_type\": \"input\",\n" +
                "      \"keyboard\": \"text\",\n" +
                "      \"required\": false,\n" +
                "      \"is_active\": true,\n" +
                "      \"icon\": \"https://jemala.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"field_id\": 89,\n" +
                "      \"hint\": \"Birthday\",\n" +
                "      \"field_type\": \"chooser\",\n" +
                "      \"required\": false,\n" +
                "      \"is_active\": true,\n" +
                "      \"icon\": \"https://jemala.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"field_id\": 898,\n" +
                "      \"hint\": \"Gender\",\n" +
                "      \"field_type\": \"chooser\",\n" +
                "      \"required\": \"false\",\n" +
                "      \"is_active\": true,\n" +
                "      \"icon\": \"https://jemala.png\"\n" +
                "    }\n" +
                "  ]\n" +
                "]"
        val fieldType = object : TypeToken<List<List<Field>>>() {}.type
        val fieldList: List<List<Field>> =
            Gson().fromJson(json, fieldType)
        data = fieldList.toMutableList()
    }

    fun requiredValidation(requiredData: Map<String, String>): Boolean {
        for (i in requiredData) {
            if (i.value.isEmpty()) {
                emptyRequiredField = i.key
                return false
            }
        }
        return true
    }
}