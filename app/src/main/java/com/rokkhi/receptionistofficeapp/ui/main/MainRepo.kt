package com.rokkhi.receptionistofficeapp.ui.main

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.*
import com.rokkhi.receptionistofficeapp.statics.EmployeeEntryStatus
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi) {

    private val disposable = CompositeDisposable()

    fun getUserByPhone(phoneNumber: String): LiveData<ApiResponse<UserResponse>> {
        val map = HashMap<String, String>()
        map["phoneNumber"] = phoneNumber
        return object : NetworkBoundResource<UserResponse>() {
            override fun createCall(): Single<UserResponse> = api.getUserByPhone(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun onClear() {
        disposable.clear()
    }

    fun addVisitor(companyId: Int, name: String, address: String, contact: String, email: String, purpose: String, image: String, thumbImage: String, branchId: Int, departmentId: Int, receptionistId: Int, responderId: Int): LiveData<ApiResponse<AddVisitorResponse>> {
        val map = HashMap<String, Any>()
        map["companyId"] = companyId
        map["name"] = name
        map["address"] = address
        map["contact"] = contact
        map["email"] = email
        map["purpose"] = purpose
        map["image"] = image
        map["thumbImage"] = thumbImage
        map["branchId"] = branchId
        map["departmentId"] = departmentId
        map["receptionistId"] = receptionistId
        map["responderId"] = responderId
        return object : NetworkBoundResource<AddVisitorResponse>() {
            override fun createCall(): Single<AddVisitorResponse> = api.addVisitor(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

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

    fun getVisitors(companyId: Int): LiveData<ApiResponse<GetVisitorsResponse>> {
        val map = HashMap<String, Any>()
        map["companyId"] = companyId
        return object : NetworkBoundResource<GetVisitorsResponse>() {
            override fun createCall(): Single<GetVisitorsResponse> = api.getVisitors(map)
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

    fun changeVisitorStatus(visitorId: Int, newStatus: String): LiveData<ApiResponse<ChangeParcelsStatusResponse>> {
        val map = HashMap<String, Any>()
        map["visitorId"] = visitorId
        map["newStatus"] = newStatus
        return object : NetworkBoundResource<ChangeParcelsStatusResponse>() {
            override fun createCall(): Single<ChangeParcelsStatusResponse> = api.changeVisitorStatus(map)
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

    fun recordEmployeeEntry(companyId: Int, employeeId: Int, departmentId: Int, branchId: Int, receptionistId: Int, acknowledgedBy: Int, status: EmployeeEntryStatus): LiveData<ApiResponse<EmployeeEntryChangeResponse>> {
        val map = HashMap<String, Any>()
        map["companyId"] = companyId
        map["employeeId"] = employeeId
        map["departmentId"] = departmentId
        map["branchId"] = branchId
        map["receptionistId"] = receptionistId
        map["acknowledgedBy"] = acknowledgedBy
        map["status"] = status
        return object : NetworkBoundResource<EmployeeEntryChangeResponse>() {
            override fun createCall(): Single<EmployeeEntryChangeResponse> = api.recordEmployeeEntry(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }


}