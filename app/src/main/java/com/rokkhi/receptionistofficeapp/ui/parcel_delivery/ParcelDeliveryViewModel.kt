package com.rokkhi.receptionistofficeapp.ui.parcel_delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetParcelsResponse
import com.rokkhi.receptionistofficeapp.networkmodel.ParcelReceivedResponse
import javax.inject.Inject

class ParcelDeliveryViewModel @Inject constructor(private val repo:ParcelDeliveredRepo) : ViewModel() {

    fun getParcels(companyId: Int): LiveData<ApiResponse<GetParcelsResponse>> = repo.getParcels(companyId)

    fun markParcelAsReceived(parcelId: Int): LiveData<ApiResponse<ParcelReceivedResponse>> = repo.markParcelAsReceived(parcelId)

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }

}