package com.gahov.musenergy.feature.profile

import androidx.lifecycle.LiveData
import com.gahov.domain.entities.user.UserEntity
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.arch.lifecycle.SingleLiveEvent
import java.time.LocalDate
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : BaseViewModel() {

    private val _userData = SingleLiveEvent<UserEntity>()
    val userData: LiveData<UserEntity>
        get() = _userData

    init {
        getUserData()
    }

    //TODO temp hardcoded data
    private fun getUserData() {
        _userData.postValue(
            UserEntity(
                id = 1L,
                name = "Danil",
                lastName = "Gahov",
                birthDate = LocalDate.parse("2000-12-08"),
                email = "danil@gahov.com",
                avatar = "https://i.ibb.co/Rk2sXWNw/photo-2023-06-26-15-12-31.jpg"
            )
        )
    }
}