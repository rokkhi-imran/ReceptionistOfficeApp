package com.rokkhi.receptionistofficeapp.ui.visitor_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.AddVisitorResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeListResponse
import javax.inject.Inject

class VisitorInViewModel @Inject constructor(private var repo: VisitorInRepo) : ViewModel() {


    fun addVisitor(requesterProfileId: Int,limit: String,pageId: String,companyId: Int, name: String, company: String, address: String,contact: String,email: String, purpose: String, image: String, thumbImage: String, branchId: Int, departmentId: Int, receptionistId: Int,responderId: Int,  associatedEmployee: Int): LiveData<ApiResponse<AddVisitorResponse>>
            = repo.addVisitor(requesterProfileId,limit,pageId,companyId, name, company, address,contact ,email, purpose, image, thumbImage, branchId, departmentId, receptionistId,responderId,  associatedEmployee)


    override fun onCleared() {
        super.onCleared()
    }

    fun getEmployeeList(requesterProfileId: Int, limit: String, pageId: String, companyId: Int, branchId: String, departmentId: String,
                        employeeRoleCode: String, employeeId: String): LiveData<ApiResponse<EmployeeListResponse>> = repo.getEmployeeList(requesterProfileId, limit, pageId, companyId, branchId, departmentId, employeeRoleCode, employeeId)


}