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



//    fun getUserDetails(deviceToken: String, deviceName: String): LiveData<ApiResponse<UserResponse>> = repo.getUserDetails(deviceToken,deviceName)


    // *******
    // note: apis can be called with Transformations.map so that data can be manipulated inside view model
    // *******
    fun getUserDetails(deviceToken: String, deviceName: String): LiveData<ApiResponse<UserResponse>> {
        return Transformations.map(repo.getUserDetails(deviceToken,deviceName)) {
            when (it) {
                is ApiResponse.Success -> {

                    shf.putString(KeyFrame.USER_NAME, it.data.data.name)
                    shf.putString(KeyFrame.USER_ID, it.data.data.id.toString())
                    shf.putString(KeyFrame.BRANCH_ID, it.data.data.branchId.toString())
                    shf.putString(KeyFrame.COMPANY_ID, it.data.data.companyId.toString())
                    shf.putString(KeyFrame.DEPARTMENT_ID, it.data.data.departmentId.toString())

                    Timber.e("userName from server: ${shf.getString(KeyFrame.USER_NAME)}")
                }
                is ApiResponse.Progress -> {}
                is ApiResponse.Failure -> {}
                is ApiResponse.ErrorCode -> {}
            }
            it
        }
    }



    override fun onCleared() {
        super.onCleared()
        repo.onClear()
    }

}