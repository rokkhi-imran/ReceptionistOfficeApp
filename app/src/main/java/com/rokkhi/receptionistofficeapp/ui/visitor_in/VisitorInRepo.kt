package com.rokkhi.receptionistofficeapp.ui.visitor_in

import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.AddVisitorResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeListResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class VisitorInRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi) {

    private val disposable = CompositeDisposable()

    fun addVisitor(requesterProfileId: Int,limit: String,pageId: String,companyId: Int, name: String, company: String, address: String,contact: String,email: String, purpose: String, image: String, thumbImage: String, branchId: Int,
                   departmentId: Int, receptionistId: Int,responderId: Int,  associatedEmployee: Int): LiveData<ApiResponse<AddVisitorResponse>> {
        val map = HashMap<String, Any>()
        map["requesterProfileId"] = requesterProfileId
        map["limit"] = limit
        map["pageId"] = pageId
        map["companyId"] = companyId
        map["name"] = name
        map["company"] = company
        map["address"] = address
        map["contact"] = contact
        map["email"] = email
        map["purpose"] = purpose
        map["image"] = image
        map["thumbImage"] = thumbImage
        map["branchId"] = branchId
        map["departmentId"] = departmentId
        map["departmentId"] = departmentId
        map["receptionistId"] = receptionistId
        map["responderId"] = responderId
        map["associatedEmployee"] = associatedEmployee
        return object : NetworkBoundResource<AddVisitorResponse>() {
            override fun createCall(): Single<AddVisitorResponse> = api.addVisitor(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()
    }

    fun getEmployeeList(requesterProfileId: Int, limit: String, pageId: String, companyId: Int, branchId: String, departmentId: String, employeeRoleCode: String,
                        employeeId: String): LiveData<ApiResponse<EmployeeListResponse>> {
        val map = HashMap<String, Any>()
        map["requesterProfileId"] = requesterProfileId
        map["limit"] = limit
        map["pageId"] = pageId
        map["companyId"] =companyId
        map["branchId"] =branchId
        map["departmentId"] =departmentId
        map["employeeRoleCode"] =employeeRoleCode
        map["employeeId"] =employeeId

        return object : NetworkBoundResource<EmployeeListResponse>() {
            override fun createCall(): Single<EmployeeListResponse> = api.getEmployeeList(map)
            override fun createDisposable(): CompositeDisposable = disposable
        }.asLiveData()

    }


}