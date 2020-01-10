package com.example.tugasstarbuck.menu

import com.google.gson.annotations.SerializedName

data class Coffee (
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("updated_at")
    val updated_at: Any? = null,

    @field:SerializedName("created_at")
    val created_at: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null
)