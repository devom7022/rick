package com.example.rickandmorttytest.model.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

    @Expose
    @SerializedName("info")
    val info: Info,

    @Expose
    @SerializedName("results")
    val results: List<Results>
)