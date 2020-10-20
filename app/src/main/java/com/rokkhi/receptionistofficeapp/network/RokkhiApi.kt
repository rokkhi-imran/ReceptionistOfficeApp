package com.rokkhi.receptionistofficeapp.network

import com.rokkhi.receptionistofficeapp.networkmodel.UserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface RokkhiApi {

    @POST("/api/v1/user/getByPhoneNumber")
    fun getUserByPhone(@Body data: Map<String, String>): Single<UserResponse>


/*

    @POST("/token/")
    fun refreshToken(@Body data: Map<String, String>): Call<LoginResponse>

    @GET("/api/data/")
    fun getSurveyInfo(): Single<SurveyInfoResponse>

    @Multipart
    @POST("/api/uploadimage/")
    fun uploadImage(@Part image: MultipartBody.Part): Single<SurveySubmitResponse>
*/

}