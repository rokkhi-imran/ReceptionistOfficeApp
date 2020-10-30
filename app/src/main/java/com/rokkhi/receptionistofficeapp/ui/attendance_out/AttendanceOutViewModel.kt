package com.rokkhi.receptionistofficeapp.ui.attendance_out

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeEntryChangeResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeListResponse
import javax.inject.Inject

class AttendanceOutViewModel @Inject constructor(private var repo: AttendanceOutRepo) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }


    fun getEmployeeList(requesterProfileId: Int, limit: String, pageId: String, companyId: Int, branchId: String, departmentId: String,
                        employeeRoleCode: String, employeeId: String): LiveData<ApiResponse<EmployeeListResponse>> = repo.getEmployeeList(requesterProfileId, limit, pageId, companyId, branchId, departmentId, employeeRoleCode, employeeId)


    fun recordEmployeeAttendanceOut(requesterProfileId: Int, limit: String, pageId: String, companyId: Int,
                                     employeeId: String,
                                     branchId: String,
                                     receptionistId: String,
                                     acknowledgedBy: String,
                                     status: String,
                                     associatedLoggedinDeviceId: String): LiveData<ApiResponse<EmployeeEntryChangeResponse>> = repo.recordEmployeeAttendanceOut(
        requesterProfileId, limit, pageId, companyId,
        employeeId,
        branchId,
        receptionistId,
        acknowledgedBy,
        status,
        associatedLoggedinDeviceId)

}