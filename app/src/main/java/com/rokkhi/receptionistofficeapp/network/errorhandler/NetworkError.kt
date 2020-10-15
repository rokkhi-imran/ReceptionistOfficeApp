package com.rokkhi.receptionistofficeapp.network.errorhandler

import com.google.gson.JsonParser
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object NetworkError {
    fun getError(throwable: Throwable):String{
        when (throwable) {
            is SocketTimeoutException -> return "Whoops! connection time out, try again!"
            is IOException -> return "No internet connection, try again!"
            is HttpException -> return try {
                val errorJsonString = throwable.response()!!.errorBody()?.string()
                JsonParser().parse(errorJsonString).asJsonObject["message"].asString
            }catch (e:Exception){
                "Unknown error occur, please try again!"
            }
        }
        return "Unknown error occur, please try again!"
    }
}