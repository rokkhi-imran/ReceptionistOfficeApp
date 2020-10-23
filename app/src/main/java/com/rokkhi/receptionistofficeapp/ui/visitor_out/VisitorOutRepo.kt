package com.rokkhi.receptionistofficeapp.ui.visitor_out

import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.ChangeVisitorStatusResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetVisitorsResponse
import com.rokkhi.receptionistofficeapp.statics.VisitorStatus
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VisitorOutRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi) {

    private val disposable = CompositeDisposable()

    fun getVisitors(): LiveData<ApiResponse<GetVisitorsResponse>> {
        return object : NetworkBoundResource<GetVisitorsResponse>() {
            override fun createCall(): Single<GetVisitorsResponse> = api.getVisitors()
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun changeVisitorStatus(visitorId: Int, newStatus: VisitorStatus): LiveData<ApiResponse<ChangeVisitorStatusResponse>> {
        val map = HashMap<String, Any>()
        map["visitorId"] = visitorId
        map["newStatus"] = newStatus
        return object : NetworkBoundResource<ChangeVisitorStatusResponse>() {
            override fun createCall(): Single<ChangeVisitorStatusResponse> = api.changeVisitorStatus(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun onCleared() {
        disposable.clear()
    }
}