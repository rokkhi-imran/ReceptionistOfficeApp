package com.rokkhi.receptionistofficeapp.ui.main

import androidx.lifecycle.LiveData
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import com.rokkhi.receptionistofficeapp.network.RokkhiApi
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.network.wrapper.NetworkBoundResource
import com.rokkhi.receptionistofficeapp.networkmodel.UserResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainRepo @Inject constructor(@RokkhiApiUrl var api: RokkhiApi){

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


}