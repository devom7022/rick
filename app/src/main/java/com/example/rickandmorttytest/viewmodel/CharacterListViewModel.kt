package com.example.rickandmorttytest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorttytest.api.ApiClient
import com.example.rickandmorttytest.app.App
import com.example.rickandmorttytest.model.response.CharacterResponse
import com.example.rickandmorttytest.model.response.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterListViewModel : ViewModel() {
    var responseCharacters = MutableLiveData<List<Results>>()

    var showMsgSnack = MutableLiveData<String>()

    @Inject
    lateinit var apiClient: ApiClient

    init {
        App.appComponent.inject(this)
    }

    fun getCharacterRM(page: String) {

        val call: Call<CharacterResponse> = apiClient.getCharacters(page)
        call.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                when (response.code()) {
                    200 -> {
                        if (response.body() != null){
                            responseCharacters.value = response.body()!!.results
                        }else{
                            showMsgSnack.value = "Non data"
                        }

                    }
                    400 -> showMsgSnack.value = "Something missing"
                    422 -> showMsgSnack.value = "information cannot be retrieve"
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                showMsgSnack.value = "Check your connection"
            }

        })
    }
}