package com.netoloboapps.mygithubfollowers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.netoloboapps.mygithubfollowers.ui.mainscreen.FollowersListScreen
import com.netoloboapps.mygithubfollowers.ui.mainscreen.FollowersListViewModel
import com.netoloboapps.mygithubfollowers.ui.theme.MyGithubFollowersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMGithubApp()
        }
    }

    @Composable
    fun MVVMGithubApp() {
        val viewModel: FollowersListViewModel = viewModel()
        MyGithubFollowersTheme {
            FollowersListScreen(
                state = viewModel.state.value
            )
        }
    }
}