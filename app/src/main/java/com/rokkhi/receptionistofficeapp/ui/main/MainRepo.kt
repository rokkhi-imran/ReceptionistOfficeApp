package com.rokkhi.receptionistofficeapp.ui.main

import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.UserResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi) {

    private val disposable = CompositeDisposable()

    fun getUserDetails(deviceToken: String, deviceName: String): LiveData<ApiResponse<UserResponse>> {
        val map = HashMap<String, String>()
        map["deviceToken"] = deviceToken
        map["deviceType"] = deviceName

        return object : NetworkBoundResource<UserResponse>() {
            override fun createCall(): Single<UserResponse> = api.getUserDetails(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun onClear() {
        disposable.clear()
    }

/*
    fun getVisitors(companyId: Int): LiveData<ApiResponse<GetVisitorsResponse>> {
        val map = HashMap<String, Any>()
        map["companyId"] = companyId
        return object : NetworkBoundResource<GetVisitorsResponse>() {
            override fun createCall(): Single<GetVisitorsResponse> = api.getVisitors(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun getParcels(companyId: Int): LiveData<ApiResponse<GetParcelsResponse>> {
        val map = HashMap<String, Any>()
        map["companyId"] = companyId
        return object : NetworkBoundResource<GetParcelsResponse>() {
            override fun createCall(): Single<GetParcelsResponse> = api.getParcels(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun changeVisitorStatus(visitorId: Int, newStatus: String): LiveData<ApiResponse<ChangeParcelsStatusResponse>> {
        val map = HashMap<String, Any>()
        map["visitorId"] = visitorId
        map["newStatus"] = newStatus
        return object : NetworkBoundResource<ChangeParcelsStatusResponse>() {
            override fun createCall(): Single<ChangeParcelsStatusResponse> = api.changeVisitorStatus(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun markParcelAsReceived(parcelId: Int): LiveData<ApiResponse<ParcelReceivedResponse>> {
        val map = HashMap<String, Any>()
        map["parcelId"] = parcelId
        return object : NetworkBoundResource<ParcelReceivedResponse>() {
            override fun createCall(): Single<ParcelReceivedResponse> = api.markParcelAsReceived(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    // implement api like this
    //    fun recordEmployeeEntry(companyId: Int, employeeId: Int, departmentId: Int, branchId: Int, receptionistId: Int, acknowledgedBy: Int, status: String): LiveData<ApiResponse<EmployeeEntryChangeResponse>>
    //        = repo.recordEmployeeEntry(companyId, employeeId, departmentId, branchId, receptionistId, acknowledgedBy, KeyFrame.KEY_INSIDE)
    fun recordEmployeeEntry(companyId: Int, employeeId: Int, departmentId: Int, branchId: Int, receptionistId: Int, acknowledgedBy: Int, status: String): LiveData<ApiResponse<EmployeeEntryChangeResponse>> {
        val map = HashMap<String, Any>()
        map["companyId"] = companyId
        map["employeeId"] = employeeId
        map["departmentId"] = departmentId
        map["branchId"] = branchId
        map["receptionistId"] = receptionistId
        map["acknowledgedBy"] = acknowledgedBy
        map["status"] = status
        return object : NetworkBoundResource<EmployeeEntryChangeResponse>() {
            override fun createCall(): Single<EmployeeEntryChangeResponse> = api.recordEmployeeEntry(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }
*/


}