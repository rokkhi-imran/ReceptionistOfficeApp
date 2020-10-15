package com.rokkhi.receptionistofficeapp.networkmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


// login data class
data class LoginResponse(val active_status: String, val msg: String, @SerializedName("surveyor_id ") val surveyor_id: Int, val token: String) : Serializable


//post answer
@Parcelize
data class PostDataClass (
    val ans : List<Ans>,
    val created_at : Long,
    val lat_lon : String,
    val survey_id : Int,
    val updated_at : Long
) : Parcelable

@Parcelize
data class Ans (
    val q_ans : String,
    val question : Int,
    val millis : Long
) : Parcelable