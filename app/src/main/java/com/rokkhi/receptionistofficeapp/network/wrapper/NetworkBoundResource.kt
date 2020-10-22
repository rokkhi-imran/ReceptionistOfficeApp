package com.rokkhi.receptionistofficeapp.network.wrapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rokkhi.receptionistofficeapp.network.errorhandler.NetworkError
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class NetworkBoundResource<ResultType>{

    private val result = MutableLiveData<ApiResponse<ResultType>>()
    init {
        fetchDataFromNetwork()
    }


    private fun fetchDataFromNetwork(){
        createDisposable().add(
            createCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { setValue(ApiResponse.loading(true)) }
                .doOnEvent { _, _ -> setValue(ApiResponse.loading(false)) }
                .subscribeWith(object : DisposableSingleObserver<ResultType>(){
                    override fun onSuccess(data: ResultType) {
//                        Timber.e("server data response : ${data.toString()}")
                        result.value = ApiResponse.success(data)
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e.localizedMessage)
                        result.value = ApiResponse.failure(Throwable(NetworkError.getError(e)))
                        result.value = ApiResponse.errorCode(e)
                    }

                })
        )
    }

    private fun setValue(newValue: ApiResponse<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }


    protected abstract fun createCall():Single<ResultType>
    protected abstract fun createDisposable():CompositeDisposable
    fun asLiveData() = result as LiveData<ApiResponse<ResultType>>
}

