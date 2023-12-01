package com.example.tbc_incomplete_service_davaleba13.models

import com.google.gson.annotations.SerializedName

data class Field(
    @SerializedName("field_id")
    val id: Int,
    val hint: String,
    @SerializedName("field_type")
    val type: TYPE,
    val keyboard: KEYBOARD? = null,
    val required: Boolean,
    @SerializedName("is_active")
    val isActive: Boolean,
    val icon: String,
) {
    enum class TYPE {
        @SerializedName("input")
        INPUT,

        @SerializedName("chooser")
        CHOOSER
    }

    enum class KEYBOARD {
        @SerializedName("text")
        TEXT,

        @SerializedName("number")
        NUMBER
    }
}
