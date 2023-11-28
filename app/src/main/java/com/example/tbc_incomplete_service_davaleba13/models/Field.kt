package com.example.tbc_incomplete_service_davaleba13.models

import com.google.gson.annotations.SerializedName

data class Field(
    @SerializedName("field_id")
    val id: Int,
    val hint: String,
    @SerializedName("field_type")
    val type: String,
    val keyboard: String? = null,
    val required: Boolean,
    @SerializedName("is_active")
    val isActive: Boolean,
    val icon: String
)
