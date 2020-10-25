package com.rokkhi.receptionistofficeapp.ui.visitor_out

import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.ChangeVisitorStatusResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetVisitorsResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VisitorOutRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi) {

    private val disposable = CompositeDisposable()

    fun getVisitors(requesterProfileId: Int, limit: String, pageId: String, companyId: Int, departmentId: Int,
                    branchId: Int, status: String, fromDate: String, toDate: String): LiveData<ApiResponse<GetVisitorsResponse>> {
        val map = HashMap<String, Any>()
        map["requesterProfileId"] = requesterProfileId
        map["limit"] = limit
        map["pageId"] = pageId
        map["companyId"] = companyId
        map["departmentId"] = departmentId
        map["branchId"] = branchId
        map["status"] = status
        map["fromDate"] = fromDate
        map["toDate"] = toDate

        return object : NetworkBoundResource<GetVisitorsResponse>() {
            override fun createCall(): Single<GetVisitorsResponse> = api.getVisitors(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun changeVisitorStatus(requesterProfileId: Int, limit: String, pageId: String, companyId: Int, visitorId: Int, newStatus: String, associatedLoggedinDeviceId: String): LiveData<ApiResponse<ChangeVisitorStatusResponse>> {
        val map = HashMap<String, Any>()
        map["requesterProfileId"] = requesterProfileId
        map["limit"] = limit
        map["pageId"] = pageId
        map["companyId"] = companyId
        map["visitorId"] = visitorId
        map["newStatus"] = newStatus
        map["associatedLoggedinDeviceId"] = associatedLoggedinDeviceId


        return object : NetworkBoundResource<ChangeVisitorStatusResponse>() {
            override fun createCall(): Single<ChangeVisitorStatusResponse> = api.changeVisitorStatus(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun onCleared() {
        disposable.clear()
    }
}