package com.rokkhi.receptionistofficeapp.ui.visitor_out

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.ChangeVisitorStatusResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetVisitorsResponse
import javax.inject.Inject

class VisitorOutViewModel @Inject constructor(private val repo: VisitorOutRepo) : ViewModel() {

    fun getVisitors(
        requesterProfileId: Int,
        limit: String,
        pageId: String,
        companyId: Int,
        departmentId: Int,
        branchId: Int,
        status: String,
        fromDate: String,
        toDate: String
    ): LiveData<ApiResponse<GetVisitorsResponse>> = repo.getVisitors(
        requesterProfileId, limit, pageId, companyId,
        departmentId, branchId, status, fromDate,
        toDate
    )

    fun changeVisitorOutStatus(requesterProfileId: Int,
                               limit: String,
                               pageId: String,
                               companyId: Int,
                               visitorId: Int,
                               newStatus: String,
                               associatedLoggedinDeviceId: String,
    ): LiveData<ApiResponse<ChangeVisitorStatusResponse>> = repo.changeVisitorStatus(
        requesterProfileId,
        limit,
        pageId,
        companyId,
        visitorId,
        newStatus,
        associatedLoggedinDeviceId)

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }



}