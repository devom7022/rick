package com.example.rickandmorttytest.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location (
	@Expose @SerializedName("name") val name : String,
	@Expose @SerializedName("url") val url : String
)