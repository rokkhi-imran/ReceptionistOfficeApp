package com.rokkhi.receptionistofficeapp.ui.parcel_delivery

import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.GetParcelsResponse
import com.rokkhi.receptionistofficeapp.networkmodel.ParcelReceivedResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class ParcelDeliveredRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi) {

    private val disposable = CompositeDisposable()

    fun getParcels(requesterProfileId: Int , limit: String ,pageId: String ,companyId: Int ,
                   departmentId: Int ,branchId: Int ,fromDate: String ,toDate: String): LiveData<ApiResponse<GetParcelsResponse>> {
        val map = HashMap<String, Any>()
        map["requesterProfileId"] = requesterProfileId
        map["limit"] = limit
        map["pageId"] = pageId
        map["companyId"] = companyId
        map["departmentId"] = departmentId
        map["branchId"] = branchId
        map["fromDate"] = fromDate
        map["toDate"] = toDate
        return object : NetworkBoundResource<GetParcelsResponse>() {
            override fun createCall(): Single<GetParcelsResponse> = api.getParcels(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun markParcelAsReceived(requesterProfileId: String, limit: String, pageId: String, companyId: String, parcelId: String): LiveData<ApiResponse<ParcelReceivedResponse>> {
        val map = HashMap<String, Any>()
        map["requesterProfileId"] = requesterProfileId
        map["limit"] = limit
        map["pageId"] = pageId
        map["companyId"] = companyId
        map["parcelId"] = parcelId
        return object : NetworkBoundResource<ParcelReceivedResponse>() {
            override fun createCall(): Single<ParcelReceivedResponse> = api.markParcelAsReceived(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun onCleared() {
        disposable.clear()
    }
}