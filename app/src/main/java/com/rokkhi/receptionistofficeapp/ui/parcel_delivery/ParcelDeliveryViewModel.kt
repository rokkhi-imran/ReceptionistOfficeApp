package com.rokkhi.receptionistofficeapp.ui.parcel_delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetParcelsResponse
import com.rokkhi.receptionistofficeapp.networkmodel.ParcelReceivedResponse
import javax.inject.Inject

class ParcelDeliveryViewModel @Inject constructor(private val repo:ParcelDeliveredRepo) : ViewModel() {

    fun getParcels(requesterProfileId: Int , limit: String ,pageId: String ,companyId: Int ,
                   departmentId: Int ,branchId: Int ,fromDate: String ,toDate: String ): LiveData<ApiResponse<GetParcelsResponse>> = repo.getParcels(
        requesterProfileId , limit ,pageId ,companyId ,
        departmentId ,branchId ,fromDate ,toDate)

    fun markParcelAsReceived(requesterProfileId: String,limit:String,pageId:String,companyId:String,parcelId:String): LiveData<ApiResponse<ParcelReceivedResponse>> = repo.markParcelAsReceived(requesterProfileId,limit,pageId,companyId,parcelId)

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }

}