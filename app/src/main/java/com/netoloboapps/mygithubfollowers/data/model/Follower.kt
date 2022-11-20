package com.netoloboapps.mygithubfollowers.data.model

import com.squareup.moshi.Json

data class Follower(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "login") val login: String = "",
    @Json(name = "html_url") val url: String = "",
    @Json(name = "avatar_url") val avatarUrl: String = ""
)
