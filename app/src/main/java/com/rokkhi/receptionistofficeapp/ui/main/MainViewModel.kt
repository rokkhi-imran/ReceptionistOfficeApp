package com.rokkhi.receptionistofficeapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.helper.SharedPrefHelper
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeEntryChangeResponse
import com.rokkhi.receptionistofficeapp.networkmodel.UserResponse
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import javax.inject.Inject

class MainViewModel @Inject constructor(private var repo: MainRepo, private val shf: SharedPrefHelper) : ViewModel() {


    // *******
    // note: apis can be called directly like below
    // *******

    fun getUserDetails(deviceToken: String, deviceName: String): LiveData<ApiResponse<UserResponse>> = repo.getUserDetails(deviceToken,deviceName)


    // *******
    // note: apis can be called with Transformations.map so that data can be manipulated inside view model
    // *******
//    fun getUserByPhone(userPhoneNumber: String): LiveData<ApiResponse<UserResponse>> {
//        return Transformations.map(repo.getUserByPhone(userPhoneNumber)) {
//            when (it) {
//                is ApiResponse.Success -> {
//                    shf.putString(KeyFrame.NAME, it.data.data.name)
//                    Timber.e("userName from server: ${shf.getString(KeyFrame.NAME)}")
//                }
//                is ApiResponse.Progress -> {}
//                is ApiResponse.Failure -> {}
//                is ApiResponse.ErrorCode -> {}
//            }
//            it
//        }
//    }

    fun recordEmployeeEntry(companyId: Int, employeeId: Int, departmentId: Int, branchId: Int, receptionistId: Int, acknowledgedBy: Int, status: String): LiveData<ApiResponse<EmployeeEntryChangeResponse>>
            = repo.recordEmployeeEntry(companyId, employeeId, departmentId, branchId, receptionistId, acknowledgedBy, KeyFrame.KEY_INSIDE)


    override fun onCleared() {
        super.onCleared()
        repo.onClear()
    }

}