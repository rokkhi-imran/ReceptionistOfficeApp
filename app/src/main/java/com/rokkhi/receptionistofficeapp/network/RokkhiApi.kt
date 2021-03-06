package com.rokkhi.receptionistofficeapp.network

import com.rokkhi.receptionistofficeapp.networkmodel.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.io.File

interface RokkhiApi {

    //user - get user by auth
    @POST("/api/v1/user/getUserDetails")
    fun getUserDetails(@Body data: Map<String, String>): Single<UserResponse>

    @POST("/api/v1/user/register")
    fun postUserDetails(@Body data: Map<String, String>): Single<UserResponse>

    //entrance - addVisitor
    @POST("/api/v1/entrance/addVisitor")
    fun addVisitor(@Body data: Map<String, Any>): Single<AddVisitorResponse>

    @POST("/api/v1/entrance/addParcel")
    @JvmSuppressWildcards
    fun addParcel(@Body data: Map<String, Any>): Single<AddParcelResponse>

    @POST("/api/v1/entrance/getVisitors")
    fun getVisitors(): Single<GetVisitorsResponse>

    @POST("/api/v1/entrance/getParcels")
    @JvmSuppressWildcards
    fun getParcels(@Body data: Map<String, Any>): Single<GetParcelsResponse>

    @POST("/api/v1/entrance/changeVisitorStatus")
    @JvmSuppressWildcards
    fun changeVisitorStatus(@Body data: Map<String, Any>): Single<ChangeVisitorStatusResponse>

    // parcel received
    @POST("/api/v1/entrance/markParcelAsReceived")
    fun markParcelAsReceived(@Body data: Map<String, Any>): Single<ParcelReceivedResponse>

    // upload single image
//    @POST("/api/v1/entrance/uploadSingle")
//    fun uploadSingle(@Body data: HashMap<String, Any>): Single<UploadSingleImageResponse>
    @FormUrlEncoded
    @POST("/api/v1/entrance/uploadSingle")
    fun uploadSingle(@Field("image") image: File, @Field("folderName") folderName: String, @Field("subFolderName") subFolderName: String, @Field("fileName") fileName: String): Single<UploadSingleImageResponse>

    // upload single image
    @POST("/api/v1/entrance/recordEmployeeEntry")
    fun recordEmployeeEntry(@Body data: Map<String, Any>): Single<EmployeeEntryChangeResponse>


/*
    @Multipart
    @POST("/api/uploadimage/")
    fun uploadImage(@Part image: MultipartBody.Part): Single<SurveySubmitResponse>
*/

}