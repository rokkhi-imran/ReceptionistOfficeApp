package com.rokkhi.receptionistofficeapp.ui.parcel_received

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.AddParcelResponse
import com.rokkhi.receptionistofficeapp.networkmodel.UploadSingleImageResponse
import java.io.File
import javax.inject.Inject

class ParcelReceivedViewModel @Inject constructor(private var repo: ParcelReceivedRepo) : ViewModel() {

    fun addParcel(companyId: Int, name: String, company: String, image: String, thumbImage: String, associatedEmployee: Int, receptionistId: Int, departmentId: Int, branchId: Int): LiveData<ApiResponse<AddParcelResponse>>
            = repo.addParcel(companyId, name, company, image, thumbImage, associatedEmployee, receptionistId, departmentId, branchId)

    fun uploadSingle(image: File, folderName: String, subFolderName: String, fileName: String): LiveData<ApiResponse<UploadSingleImageResponse>>
            = repo.uploadSingle(image, folderName, subFolderName, fileName)

    override fun onCleared() {
        super.onCleared()
    }

}