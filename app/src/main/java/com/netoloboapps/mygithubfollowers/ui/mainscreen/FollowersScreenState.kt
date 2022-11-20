package com.netoloboapps.mygithubfollowers.ui.mainscreen

import com.netoloboapps.mygithubfollowers.data.model.Follower

data class FollowersScreenState(
    val followers : List<Follower>,
    val isLoading : Boolean,
    val error: String? = null
)