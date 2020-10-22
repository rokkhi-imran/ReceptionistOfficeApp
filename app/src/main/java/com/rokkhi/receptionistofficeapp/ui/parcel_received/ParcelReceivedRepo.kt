package com.rokkhi.receptionistofficeapp.ui.parcel_received

import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.AddParcelResponse
import com.rokkhi.receptionistofficeapp.networkmodel.UploadSingleImageResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import javax.inject.Inject


class ParcelReceivedRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi) {

    private val disposable = CompositeDisposable()

    fun addParcel(companyId: Int, name: String, company: String, image: String, thumbImage: String, associatedEmployee: Int, receptionistId: Int, departmentId: Int, branchId: Int): LiveData<ApiResponse<AddParcelResponse>> {
        val map = HashMap<String, Any>()
        map["companyId"] = companyId
        map["name"] = name
        map["company"] = company
        map["image"] = image
        map["thumbImage"] = thumbImage
        map["associatedEmployee"] = associatedEmployee
        map["receptionistId"] = receptionistId
        map["departmentId"] = departmentId
        map["branchId"] = branchId
        return object : NetworkBoundResource<AddParcelResponse>() {
            override fun createCall(): Single<AddParcelResponse> = api.addParcel(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun uploadSingle(image: File, folderName: String, subFolderName: String, fileName: String): LiveData<ApiResponse<UploadSingleImageResponse>> {
        return object : NetworkBoundResource<UploadSingleImageResponse>() {
            override fun createCall(): Single<UploadSingleImageResponse> = api.uploadSingle(image, folderName, subFolderName, fileName)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

}