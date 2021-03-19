package com.example.rickandmorttytest.di.component

import com.example.rickandmorttytest.di.module.AppModule
import com.example.rickandmorttytest.di.module.RetrofitModule
import com.example.rickandmorttytest.viewmodel.CharacterListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class])
interface AppComponent {

    fun inject(activity: CharacterListViewModel)
}