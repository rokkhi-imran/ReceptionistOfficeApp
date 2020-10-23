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

    fun getParcels(companyId: Int): LiveData<ApiResponse<GetParcelsResponse>> {
        val map = HashMap<String, Any>()
        map["companyId"] = companyId
        return object : NetworkBoundResource<GetParcelsResponse>() {
            override fun createCall(): Single<GetParcelsResponse> = api.getParcels(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun markParcelAsReceived(parcelId: Int): LiveData<ApiResponse<ParcelReceivedResponse>> {
        val map = HashMap<String, Any>()
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