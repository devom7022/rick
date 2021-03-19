package com.example.rickandmorttytest.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Info(
    @Expose @SerializedName("count") val count: Int?=0,
    @Expose @SerializedName("pages") val pages: Int?=0,
    @Expose @SerializedName("next") val next: String?=null,
    @Expose @SerializedName("prev") val prev: String?=null
)