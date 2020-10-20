package com.rokkhi.receptionistofficeapp.networkmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


// login data class
data class LoginResponse(val active_status: String, val msg: String, @SerializedName("surveyor_id ") val surveyor_id: Int, val token: String) : Serializable

// user response
data class UserResponse(val `data`: Data, val errorCode: Int, val errors: String, val message: String, val path: String, val status: String, val statusCode: Int, val timestamp: String): Serializable
data class Data(val address: String, val age: Int, val branch: Any, val company: Company, val contactPersonName: String, val contactPersonPhone: String, val createdDate: String, val deletedDate: Any, val department: Any, val email: String, val firebaseId: String, val gender: String, val id: Int, val image: String, val isActive: Boolean, val name: String, val nid: String, val password: String, val phone: String, val primaryRoleCode: String, val thumbImage: String, val updatedDate: String): Serializable
data class Company(val address: String, val contactInfo: String, val contactPerson: String, val createdDate: String, val deletedDate: Any, val email: String, val firebaseId: String, val id: Int, val isActive: Boolean, val latitude: Any, val longitude: Any, val name: String, val password: String, val type: String, val updatedDate: String): Serializable

//post answer
@Parcelize
data class PostDataClass(
    val ans: List<Ans>,
    val created_at: Long,
    val lat_lon: String,
    val survey_id: Int,
    val updated_at: Long
) : Parcelable

@Parcelize
data class Ans(
    val q_ans: String,
    val question: Int,
    val millis: Long
) : Parcelable



/*

{
    "status": "error",
    "statusCode": 460,
    "message": "Some Error Occurred",
    "path": "/api/v1/user/getByPhoneNumber",
    "timestamp": "2020-10-20T06:23:52.949Z",
    "errorCode": 10013,
  "data": {
    "firebaseId": "IlicPMfNilMCQEwhz5NeGygh8Sr1",
    "name": "Rokkhi",
    "phone": "string",
    "email": "rokkhi@test1123.com",
    "address": "banani",
    "nid": "",
    "password": "12345678",
    "gender": "",
    "age": 0,
    "contactPersonPhone": "",
    "contactPersonName": "",
    "image": "",
    "thumbImage": "",
    "primaryRoleCode": "1001",
    "isActive": true,
    "id": 1,
    "createdDate": "2020-10-19T04:40:59.972Z",
    "updatedDate": "2020-10-19T04:40:59.972Z",
    "deletedDate": null,
    "company": {
      "id": 1,
      "createdDate": "2020-10-19T04:40:59.406Z",
      "updatedDate": "2020-10-19T04:40:59.406Z",
      "deletedDate": null,
      "firebaseId": "IlicPMfNilMCQEwhz5NeGygh8Sr1",
      "name": "Rokkhi",
      "address": "banani",
      "contactPerson": "m",
      "contactInfo": "string",
      "email": "rokkhi@test1123.com",
      "password": "12345678",
      "latitude": null,
      "longitude": null,
      "type": "PRIVATE",
      "isActive": true
    },
    "branch": null,
    "department": null
  },
  "errors": ""
}

*/
