package com.example.rickandmorttytest.api

import com.example.rickandmorttytest.model.response.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("character")
    fun getCharacters(@Query("page") page: String): Call<CharacterResponse>
}