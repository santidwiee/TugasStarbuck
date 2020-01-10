package com.example.tugasstarbuck.menu

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("coffee")
    val coffee: List<Coffee>
)

