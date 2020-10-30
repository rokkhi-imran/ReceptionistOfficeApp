package com.rokkhi.receptionistofficeapp.ui.attendance_in

import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeEntryChangeResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeListResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class AttendanceInRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi) {

    private val disposable = CompositeDisposable()

    fun getEmployeeList(requesterProfileId: Int, limit: String, pageId: String, companyId: Int, branchId: String, departmentId: String, employeeRoleCode: String,
                        employeeId: String): LiveData<ApiResponse<EmployeeListResponse>> {
        val map = HashMap<String, Any>()
        map["requesterProfileId"] = requesterProfileId
        map["limit"] = limit
        map["pageId"] = pageId
        map["companyId"] =companyId
        map["branchId"] =branchId
        map["departmentId"] =departmentId
        map["employeeRoleCode"] =employeeRoleCode
        map["employeeId"] =employeeId

        return object : NetworkBoundResource<EmployeeListResponse>() {
            override fun createCall(): Single<EmployeeListResponse> = api.getEmployeeList(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()

    }
    fun recordEmployeeAttendanceIn(requesterProfileId: Int, limit: String, pageId: String, companyId: Int,
                                     employeeId: String,
                                     branchId: String,
                                     receptionistId: String,
                                     acknowledgedBy: String,
                                     status: String,
                                     associatedLoggedinDeviceId: String): LiveData<ApiResponse<EmployeeEntryChangeResponse>> {
        val map = HashMap<String, Any>()
        map["requesterProfileId"] = requesterProfileId
        map["limit"] = limit
        map["pageId"] = pageId
        map["companyId"] =companyId
        map["employeeId"] =employeeId
        map["branchId"] =branchId

        map["receptionistId"] =receptionistId
        map["acknowledgedBy"] =acknowledgedBy
        map["status"] =status
        map["associatedLoggedinDeviceId"] =associatedLoggedinDeviceId

        return object : NetworkBoundResource<EmployeeEntryChangeResponse>() {
            override fun createCall(): Single<EmployeeEntryChangeResponse> = api.recordEmployeeEntry(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()

    }


}