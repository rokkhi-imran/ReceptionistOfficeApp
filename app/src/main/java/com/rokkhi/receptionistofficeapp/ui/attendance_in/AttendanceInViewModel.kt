package com.rokkhi.receptionistofficeapp.ui.attendance_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeListResponse
import com.rokkhi.receptionistofficeapp.ui.visitor_in.VisitorInRepo
import javax.inject.Inject

class AttendanceInViewModel @Inject constructor(private var repo: AttendanceInRepo) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }


    fun getEmployeeList(requesterProfileId: Int, limit: String, pageId: String, companyId: Int, branchId: String, departmentId: String,
                        employeeRoleCode: String, employeeId: String): LiveData<ApiResponse<EmployeeListResponse>> = repo.getEmployeeList(requesterProfileId, limit, pageId, companyId, branchId, departmentId, employeeRoleCode, employeeId)


    fun recordEmployeeAttendanceList(requesterProfileId: Int, limit: String, pageId: String, companyId: Int,
                                     employeeId: String,
                                     departmentId: String,
                                     branchId: String,
                                     receptionistId: String,
                                     acknowledgedBy: String,
                                     status: String,
                                     associatedLoggedinDeviceId: String): LiveData<ApiResponse<EmployeeListResponse>> = repo.recordEmployeeAttendanceList(
        requesterProfileId, limit, pageId, companyId,
        employeeId,
        departmentId,
        branchId,
        receptionistId,
        acknowledgedBy,
        status,
        associatedLoggedinDeviceId)

}