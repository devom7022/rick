package com.example.rickandmorttytest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.rickandmorttytest.app.App

import com.example.rickandmorttytest.model.SessionRunTime

class LoginViewModel : ViewModel() {

    var sessionRunTime: MutableLiveData<SessionRunTime> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    var validationResponse = MutableLiveData<Int>()

    fun login(username: String, password: String) {
        validateFields(username, password)
    }

    fun validateFields(username: String, password: String) {
        return when {
            !isUserNameValid(username) -> {
                validationResponse.value = 1
            }

            !isPasswordValid(password) -> {
                validationResponse.value = 2
            }

            else -> {
                App.instance.setSession(username, password)
                validationResponse.value = 0
            }
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return when {
            username.isNotBlank() && username.contains('@') && username.isNotEmpty() -> {
                Patterns.EMAIL_ADDRESS.matcher(username).matches()
            }
            username.length > 4 -> {
                true
            }
            else -> {
                false
            }
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty() && password.isNotBlank() && password.length > 4
    }

}