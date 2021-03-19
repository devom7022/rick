package com.example.rickandmorttytest.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorttytest.databinding.ActivityLoginBinding
import com.example.rickandmorttytest.model.SessionRunTime
import com.example.rickandmorttytest.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        observeViewModel()

        binding.btSignin.setOnClickListener {
            loginViewModel.login(
                binding.tietEmail.text.toString(),
                binding.tietPassword.text.toString()
            )
        }

    }

    private fun observeViewModel() {
        loginViewModel.sessionRunTime.observe(
            this,
            Observer<SessionRunTime> { session ->
                Log.i("Session ", session.toString())
            }
        )

        loginViewModel.isLoading.observe(this,
            Observer<Boolean> {
                if (it) {
                    binding.pbLoading.visibility = View.VISIBLE
                } else {
                    binding.pbLoading.visibility = View.GONE
                }
            })

        loginViewModel.validationResponse.observe(this, {
            when (it) {
                0 -> launchMain()//Success
                1 -> binding.tietEmail.error = "Invalid email"//Email error
                2 -> binding.tietPassword.error =
                    "Password must longer than 5 characters"//Password error

            }
        })
    }

    private fun launchMain() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        startActivity(intent)
    }
}