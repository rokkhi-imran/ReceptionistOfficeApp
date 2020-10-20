package com.rokkhi.receptionistofficeapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.helper.SharedPrefHelper
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.UserResponse
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private var repo: MainRepo, private val shf: SharedPrefHelper) : ViewModel() {

    fun getUserByPhone(userPhoneNumber: String): LiveData<ApiResponse<UserResponse>> {
        return Transformations.map(repo.getUserByPhone(userPhoneNumber)) {
            when (it) {
                is ApiResponse.Success -> {
                    shf.putString(KeyFrame.NAME, it.data.data.name)
                    Timber.e("userName from server: ${shf.getString(KeyFrame.NAME)}")
                }
            }
            it
        }
    }


    override fun onCleared() {
        super.onCleared()
        repo.onClear()
    }

}