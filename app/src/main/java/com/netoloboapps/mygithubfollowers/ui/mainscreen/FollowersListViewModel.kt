package com.netoloboapps.mygithubfollowers.ui.mainscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netoloboapps.mygithubfollowers.data.repository.MainRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FollowersListViewModel : ViewModel() {
    private val _state = mutableStateOf(
        FollowersScreenState(
            followers = listOf(),
            isLoading = true
        )
    )

    val state: State<FollowersScreenState>
    get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

    init {
        getFollowers()
    }


    private fun getFollowers() {
        val repository = MainRepository()
        viewModelScope.launch  (errorHandler + Dispatchers.IO){
            val followers = repository.getUsers()
            withContext(Dispatchers.Main){
                _state.value = _state.value.copy(
                    followers = followers,
                    isLoading = false
                )
            }

        }
    }
}