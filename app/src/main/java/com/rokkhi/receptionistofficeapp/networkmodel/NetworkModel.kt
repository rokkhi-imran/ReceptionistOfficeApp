package com.rokkhi.receptionistofficeapp.networkmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


// login data class
data class LoginResponse(val active_status: String, val msg: String, @SerializedName("surveyor_id ") val surveyor_id: Int, val token: String) : Serializable

// user response
/*
data class UserResponse(val `data`: UserResponseData, val errorCode: Int, val message: String, val path: String, val status: String, val statusCode: Int, val timestamp: String) : Serializable
data class UserResponseData(val address: String, val age: Int, val branch: Any, val company: UserResponseCompany, val contactPersonName: String, val contactPersonPhone: String, val createdDate: String, val deletedDate: Any, val department: Any, val email: String, val firebaseId: String, val gender: String, val id: Int, val image: String, val isActive: Boolean, val name: String, val nid: String, val password: String, val phone: String, val primaryRoleCode: String, val thumbImage: String, val updatedDate: String) : Serializable
data class UserResponseCompany(val address: String, val contactInfo: String, val contactPerson: String, val createdDate: String, val deletedDate: Any, val email: String, val firebaseId: String, val id: Int, val isActive: Boolean, val latitude: Any, val longitude: Any, val name: String, val password: String, val type: String, val updatedDate: String) : Serializable
*/

//data class UserResponse(val `data`: UserResponseData, val status: String, val statusCode: Int)
//data class UserResponseData(val address: String, val age: Int, val contactPersonName: String, val contactPersonPhone: String, val createdDate: String, val deletedDate: Any, val devices: List<Device>, val email: String, val employee: List<UserEmployee>, val firebaseId: String, val gender: String, val id: Int, val image: String, val isActive: Boolean, val name: String, val nid: String, val password: String, val phone: String, val primaryRoleCode: String, val thumbImage: String, val updatedDate: String)
//data class UserEmployee(val address: String, val age: Int, val branch: UserResponseBranch, val company: UserResponseCompany, val contactPersonName: String, val contactPersonPhone: String, val createdDate: String, val deletedDate: Any, val department: UserDepartment, val email: String, val gender: String, val id: Int, val image: String, val isActive: Boolean, val name: String, val nid: String, val password: String, val phone: String, val primaryRoleCode: String = "a", val thumbImage: String, val updatedDate: String)
//data class UserResponseBranch(val address: String, val contactInfo: String, val contactPerson: String, val createdDate: String, val deletedDate: Any, val email: String, val firebaseId: String, val id: Int, val isActive: Boolean, val latitude: Int, val longitude: Int, val name: String, val password: String, val type: String, val updatedDate: String)
//data class UserResponseCompany(val address: String, val contactInfo: String, val contactPerson: String, val createdDate: String, val deletedDate: Any, val email: String, val firebaseId: String, val id: Int, val isActive: Boolean, val latitude: Any, val longitude: Any, val name: String, val password: String, val type: String, val updatedDate: String)
//data class UserDepartment(val createdDate: String, val deletedDate: Any, val description: String, val id: Int, val name: String, val updatedDate: String)


//Employee List

data class EmployeeListResponse(
    val data: List<EmployeeListData>,
    val errors: List<Any>,
    val status: String,
    val statusCode: Int
)

data class EmployeeListData(
    val address: String,
    val age: Int,
    val branch: EmployeeListBranch,
    val company: EmployeeListCompany,
    val contactPersonName: String,
    val contactPersonPhone: String,
    val createdDate: String,
    val deletedDate: Any,
    val department: EmployeeListDepartment,
    val email: String,
    val gender: String,
    val id: Int,
    val image: String,
    val isActive: Boolean,
    val name: String,
    val nid: String,
    val password: String,
    val phone: String,
    val primaryRoleCode: String,
    val thumbImage: String,
    val updatedDate: String,
    val user: Any
)

data class EmployeeListBranch(
    val address: String,
    val contactInfo: String,
    val contactPerson: String,
    val createdDate: String,
    val deletedDate: Any,
    val email: String,
    val firebaseId: String,
    val id: Int,
    val isActive: Boolean,
    val latitude: Int,
    val longitude: Int,
    val name: String,
    val password: String,
    val type: String,
    val updatedDate: String
)

data class EmployeeListCompany(
    val address: String,
    val contactInfo: String,
    val contactPerson: String,
    val createdDate: String,
    val deletedDate: Any,
    val email: String,
    val firebaseId: String,
    val id: Int,
    val isActive: Boolean,
    val latitude: Any,
    val longitude: Any,
    val name: String,
    val password: String,
    val type: String,
    val updatedDate: String
)

data class EmployeeListDepartment(
    val createdDate: String,
    val deletedDate: Any,
    val description: String,
    val id: Int,
    val name: String,
    val updatedDate: String
)

//User Response
data class UserResponse(
    val `data`: UserResponseData,
    val errors: List<Any>,
    val status: String,
    val statusCode: Int
)
data class UserResponseData(
    val address: String,
    val age: Int,
    val branchId: Int,
    val companyId: Int,
    val contactPersonName: String,
    val contactPersonPhone: String,
    val createdDate: String,
    val deletedDate: Any,
    val departmentId: Int,
    val devices: List<Device>,
    val email: String,
    val employee: List<Any>,
    val firebaseId: String,
    val gender: String,
    val id: Int,
    val image: String,
    val isActive: Boolean,
    val name: String,
    val nid: String,
    val password: String,
    val phone: String,
    val primaryRoleCode: String,
    val thumbImage: String,
    val updatedDate: String
)
data class Device(val createdDate: String, val deletedDate: Any, val deviceToken: String, val id: Int, val name: String, val type: String, val updatedDate: String)


// entrance - visitor
data class AddVisitorResponse(val `data`: AddVisitorData, val message: String, val path: String, val status: String, val statusCode: Int, val timestamp: String) : Serializable
data class AddVisitorData(val address: String, val authorizedBy: Int, val branch: Int, val company: Int, val contact: String, val createdDate: String, val deletedDate: Any, val department: Int, val email: String, val exitTime: Any, val id: Int, val image: String, val inTime: String, val name: String, val purpose: String, val receivedBy: Int, val status: String, val thumbImage: String, val type: String, val updatedDate: String) : Serializable

// entrance - parcel
data class AddParcelResponse(val `data`: AddParcelData, val message: String, val path: String, val status: String, val statusCode: Int, val timestamp: String) : Serializable
data class AddParcelData(val address: String, val associatedEmployee: Int, val branch: Int, val company: Int, val contact: String, val createdDate: String, val deletedDate: Any, val department: Int, val email: String, val exitTime: Any, val id: Int, val image: String, val inTime: String, val name: String, val purpose: String, val receivedBy: Int, val status: String, val thumbImage: String, val type: String, val updatedDate: String) : Serializable

// get visitors
data class GetVisitorsResponse(val `data`: List<GetVisitorsData>, val status: String, val statusCode: Int) : Serializable
data class GetVisitorsData(val address: String, val contact: String, val createdDate: String, val deletedDate: Any, val department: GetVisitorsDepartment, val departmentName: String, val email: String, val exitTime: Any, val id: Int, val image: String, val inTime: String, val name: String, val purpose: String, val status: String, val thumbImage: String, val type: String, val updatedDate: String) : Serializable
data class GetVisitorsDepartment(val createdDate: String, val deletedDate: Any, val description: String, val id: Int, val name: String, val updatedDate: String) : Serializable

// change visitor status response
data class ChangeVisitorStatusResponse(val `data`: ChangeVisitorStatusData, val status: String, val statusCode: Int)
data class ChangeVisitorStatusData(val address: String, val contact: String, val createdDate: String, val deletedDate: String, val email: String, val exitTime: String, val id: Int, val image: String, val inTime: String, val name: String, val purpose: String, val status: String, val thumbImage: String, val type: String, val updatedDate: String)

// get parcels
data class GetParcelsResponse(val `data`: List<GetParcelsData>, val status: String, val statusCode: Int)
data class GetParcelsData(val address: String, val associatedEmployee: GetParcelsAssociatedEmployee, val contact: String, val createdDate: String, val deletedDate: Any, val email: String, val exitTime: Any, val id: Int, val image: String, val inTime: String, val name: String, val purpose: String, val status: String, val thumbImage: String, val type: String, val updatedDate: String)
data class GetParcelsAssociatedEmployee(val address: String, val age: Int, val branch: GetParcelsBranch, val company: GetParcelsCompany, val contactPersonName: String, val contactPersonPhone: String, val createdDate: String, val deletedDate: Any, val department: GetParcelsDepartment, val email: String, val gender: String, val id: Int, val image: String, val isActive: Boolean, val name: String, val nid: String, val password: String, val phone: String, val primaryRoleCode: String, val thumbImage: String, val updatedDate: String, val user: GetParcelsUser)
data class GetParcelsBranch(val address: String, val contactInfo: String, val contactPerson: String, val createdDate: String, val deletedDate: Any, val email: String, val firebaseId: String, val id: Int, val isActive: Boolean, val latitude: Int, val longitude: Int, val name: String, val password: String, val type: String, val updatedDate: String)
data class GetParcelsCompany(val address: String, val contactInfo: String, val contactPerson: String, val createdDate: String, val deletedDate: Any, val email: String, val firebaseId: String, val id: Int, val isActive: Boolean, val latitude: Any, val longitude: Any, val name: String, val password: String, val type: String, val updatedDate: String)
data class GetParcelsDepartment(val createdDate: String, val deletedDate: Any, val description: String, val id: Int, val name: String, val updatedDate: String)
data class GetParcelsUser(val address: String, val age: Int, val branch: Int, val company: Int, val contactPersonName: String, val contactPersonPhone: String, val createdDate: String, val deletedDate: Any, val department: Int, val devices: List<Device>, val email: String, val firebaseId: String, val gender: String, val id: Int, val image: String, val isActive: Boolean, val name: String, val nid: String, val password: String, val phone: String, val primaryRoleCode: String, val thumbImage: String, val updatedDate: String)

// change parcels status
data class ChangeParcelsStatusResponse(val `data`: ChangeParcelsStatusData, val message: String, val path: String, val status: String, val statusCode: Int, val timestamp: String) : Serializable
data class ChangeParcelsStatusData(val address: String, val contact: String, val createdDate: String, val deletedDate: Any, val email: String, val exitTime: Any, val id: Int, val image: String, val inTime: String, val name: String, val purpose: String, val status: String, val thumbImage: String, val type: String, val updatedDate: String) : Serializable

// parcel received
data class ParcelReceivedResponse(val `data`: ParcelReceivedData, val message: String, val path: String, val status: String, val statusCode: Int, val timestamp: String) : Serializable
data class ParcelReceivedData(val address: String, val contact: String, val createdDate: String, val deletedDate: Any, val email: String, val exitTime: String, val id: Int, val image: String, val inTime: String, val name: String, val purpose: String, val status: String, val thumbImage: String, val type: String, val updatedDate: String) : Serializable

// upload single
data class UploadSingleImageResponse(@SerializedName("data") val imageDownloadURL: String, val message: String, val path: String, val status: String, val statusCode: Int, val timestamp: String) : Serializable

// record employee entry
/*
data class EmployeeEntryChangeResponse(val `data`: EmployeeEntryChangeData, val status: String, val statusCode: Int) : Serializable
data class EmployeeEntryChangeData(val address: String, val authorizedBy: Int, val branch: Int, val company: Int, val contact: String, val createdDate: String, val deletedDate: Any, val department: Int, val email: String, val exitTime: Any, val id: Int, val image: String, val inTime: String, val name: String, val purpose: String, val receivedBy: Int, val status: String, val thumbImage: String, val type: String, val updatedDate: String) : Serializable
*/
data class EmployeeEntryChangeResponse(
    val data: EmployeeEntryChangeData,
    val status: String,
    val statusCode: Int
)

data class EmployeeEntryChangeData(
    val address: String,
    val associatedEmployee: String,
    val associatedLoggedinDevice: EmployeeEntryChangeAssociatedLoggedinDevice,
    val authorizedBy: String,
    val branch: String,
    val company: Int,
    val contact: String,
    val createdDate: String,
    val deletedDate: Any,
    val department: EmployeeEntryChangeDepartment,
    val email: String,
    val exitTime: Any,
    val id: Int,
    val image: String,
    val inTime: String,
    val name: String,
    val purpose: String,
    val status: String,
    val thumbImage: String,
    val type: String,
    val updatedDate: String
)

data class EmployeeEntryChangeAssociatedLoggedinDevice(
    val address: String,
    val age: Int,
    val branchId: Int,
    val companyId: Int,
    val contactPersonName: String,
    val contactPersonPhone: String,
    val createdDate: String,
    val deletedDate: Any,
    val departmentId: Int,
    val email: String,
    val firebaseId: String,
    val gender: String,
    val id: Int,
    val image: String,
    val isActive: Boolean,
    val name: String,
    val nid: String,
    val password: String,
    val phone: String,
    val primaryRoleCode: String,
    val thumbImage: String,
    val updatedDate: String
)

data class EmployeeEntryChangeDepartment(
    val createdDate: String,
    val deletedDate: Any,
    val description: String,
    val id: Int,
    val name: String,
    val updatedDate: String
)



/*

{
  "status": "success",
  "statusCode": 200,
  "data": "https://kolig.s3.ap-south-1.amazonaws.com/testFolderName/testSubFolderName/testFileName",
  "errors": [],
  "message": "Some Error Occurred",
  "path": "/api/v1/entrance/changeVisitorStatus",
  "timestamp": "2020-10-21T10:12:22.900Z",
  "errorCode": 10029
}

*/



//post answer
@Parcelize
data class PostDataClass(val ans: List<Ans>, val created_at: Long, val lat_lon: String, val survey_id: Int, val updated_at: Long) : Parcelable

@Parcelize
data class Ans(val q_ans: String, val question: Int, val millis: Long) : Parcelable


