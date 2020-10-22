package com.rokkhi.receptionistofficeapp.ui.parcel_received

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.AddParcelResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetParcelsResponse
import com.rokkhi.receptionistofficeapp.networkmodel.ParcelReceivedResponse
import com.rokkhi.receptionistofficeapp.networkmodel.UploadSingleImageResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ParcelRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi) {

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

    fun uploadSingle(image: Bitmap, folderName: String, subFolderName: String, fileName: String): LiveData<ApiResponse<UploadSingleImageResponse>> {
        val map = HashMap<String, Any>()
        map["image"] = image
        map["folderName"] = folderName
        map["subFolderName"] = subFolderName
        map["fileName"] = fileName
        return object : NetworkBoundResource<UploadSingleImageResponse>() {
            override fun createCall(): Single<UploadSingleImageResponse> = api.uploadSingle(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

}