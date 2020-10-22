package com.rokkhi.receptionistofficeapp.ui.parcel_received

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.AddParcelResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetParcelsResponse
import com.rokkhi.receptionistofficeapp.networkmodel.ParcelReceivedResponse
import com.rokkhi.receptionistofficeapp.networkmodel.UploadSingleImageResponse
import javax.inject.Inject

class ParcelReceivedViewModel @Inject constructor(private var repo: ParcelRepo) : ViewModel() {

    fun addParcel(companyId: Int, name: String, company: String, image: String, thumbImage: String, associatedEmployee: Int, receptionistId: Int, departmentId: Int, branchId: Int): LiveData<ApiResponse<AddParcelResponse>> = repo.addParcel(companyId, name, company, image, thumbImage, associatedEmployee, receptionistId, departmentId, branchId)

    fun getParcels(companyId: Int): LiveData<ApiResponse<GetParcelsResponse>> = repo.getParcels(companyId)

    fun markParcelAsReceived(parcelId: Int): LiveData<ApiResponse<ParcelReceivedResponse>> = repo.markParcelAsReceived(parcelId)

    fun uploadSingle(image: Bitmap, folderName: String, subFolderName: String, fileName: String): LiveData<ApiResponse<UploadSingleImageResponse>> = repo.uploadSingle(image, folderName, subFolderName, fileName)

    override fun onCleared() {
        super.onCleared()
    }

}