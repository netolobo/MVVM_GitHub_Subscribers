package com.netoloboapps.mygithubfollowers.data.repository

import com.netoloboapps.mygithubfollowers.data.api.ApiService
import com.netoloboapps.mygithubfollowers.data.model.Follower
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class MainRepository {

    private val BASE_URL = "https://api.github.com/users/"

    //Moshi config
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    //Retrofit config
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val apiService: ApiService = retrofit.create()

    suspend fun getUsers(): List<Follower> = apiService.getUsers()
}