package com.rokkhi.receptionistofficeapp.ui.visitor_out

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.ChangeVisitorStatusResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetVisitorsResponse
import javax.inject.Inject

class VisitorOutViewModel @Inject constructor(private val repo: VisitorOutRepo) : ViewModel() {

    fun getVisitors(): LiveData<ApiResponse<GetVisitorsResponse>> = repo.getVisitors()

    fun changeVisitorOutStatus(visitorId: Int, newStatus: String): LiveData<ApiResponse<ChangeVisitorStatusResponse>> = repo.changeVisitorStatus(visitorId, newStatus)

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }

}