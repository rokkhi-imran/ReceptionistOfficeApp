package com.rokkhi.receptionistofficeapp.ui.parcel_received

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.AddParcelResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeListResponse
import javax.inject.Inject

class ParcelReceivedViewModel @Inject constructor(private var repo: ParcelReceivedRepo) : ViewModel() {

    fun addParcel(requesterProfileId: String,
                  limit: String,
                  pageId: String,
                  companyId: Int,
                  name: String,
                  company: String,
                  image: String,
                  thumbImage: String,
                  associatedLoggedinDeviceId: String,
                  associatedEmployee: Int,
                  receptionistId: Int,
                  departmentId: Int,
                  branchId: Int): LiveData<ApiResponse<AddParcelResponse>>
            = repo.addParcel(
        requesterProfileId,
        limit,
        pageId,
        companyId,
        name,
        company,
        image,
        thumbImage,
        associatedLoggedinDeviceId,
        associatedEmployee,
        receptionistId,
        departmentId,
        branchId
    )



    override fun onCleared() {
        super.onCleared()
    }


    fun getEmployeeList(requesterProfileId: Int, limit: String, pageId: String, companyId: Int, branchId: String, departmentId: String,
                        employeeRoleCode: String, employeeId: String): LiveData<ApiResponse<EmployeeListResponse>> = repo.getEmployeeList(requesterProfileId, limit, pageId, companyId, branchId, departmentId, employeeRoleCode, employeeId)


}