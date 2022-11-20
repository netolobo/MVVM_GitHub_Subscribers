package com.netoloboapps.mygithubfollowers.data.api

import com.netoloboapps.mygithubfollowers.data.model.Follower
import retrofit2.http.GET


interface ApiService {//Change your username if yout want to see your or someone else followers: your_user_name/followers?page1&per_page=1000
    @GET("michaelTadeu/followers?page1&per_page=1000")
    suspend fun getUsers(): List<Follower>
}
