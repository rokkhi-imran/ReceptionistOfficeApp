package com.rokkhi.receptionistofficeapp.ui.visitor_in

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityVisitorInBinding
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import com.rokkhi.receptionistofficeapp.util.StaticFunction
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.listeners.IPickResult
import org.json.JSONException
import org.json.JSONObject
import java.io.File

class VisitorInActivity : BaseActivity<ActivityVisitorInBinding>() ,IPickResult{

    lateinit var viewModel: VisitorInViewModel
    private var parcelPictureAsFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       dataBinding.lifecycleOwner=this

        viewModel= ViewModelProvider(this, viewModelFactory).get(VisitorInViewModel::class.java)

        dataBinding.userPhotoIV.setOnClickListener { StaticFunction.selectImage(this) }

        dataBinding.imageUploadTV.setOnClickListener { StaticFunction.selectImage(this) }

        dataBinding.SubmitUserInfoBtn.setOnClickListener { checkInputValidation() }



    }

    private fun checkInputValidation() {
        if (!StaticFunction.checkValidation(dataBinding.userNameET, dataBinding.userNameET.text, " ভিজিটর নাম ? ")) {
            return
        }

        if (!StaticFunction.checkValidation(dataBinding.phoneNoET, dataBinding.phoneNoET.text, "ফোন নম্বার দিন !")) {
            return
        }

        if (!StaticFunction.checkValidation(dataBinding.puposeET, dataBinding.puposeET.text, " কি উদ্দ্যেশে এসেছে ? ")) {
            return
        }

        if (!StaticFunction.checkValidation(dataBinding.addressET, dataBinding.addressET.text, "ঠিকানা ইনপুট দিন ! ")) {
            return
        }

        if (parcelPictureAsFile!=null){
            imageUploadApi(parcelPictureAsFile!!)
        }else{

//              callApiToUploadData(imageDownloadLink)
        }


    }


    private fun imageUploadApi(parcelPictureAsFile: File) {



        showProgressBar(true, dataBinding.progressBar)

        AndroidNetworking.upload(KeyFrame.imageUploadURL)
            .addMultipartFile("image", parcelPictureAsFile) // posting any type of file
            .addMultipartParameter("folder", "parcel")
            .addMultipartParameter("subfolder", "CompanyName")
            .addMultipartParameter("filename", System.currentTimeMillis().toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val imageDownloadLink = response.getString("url")
                        logThis(imageDownloadLink)
//                        callApiToUploadData(imageDownloadLink)


                    } catch (e: JSONException) {
                        showProgressBar(false, dataBinding.progressBar)

                        showToast("Failed to upload image ")
                    }
                }

                override fun onError(error: ANError) {
                    showProgressBar(false, dataBinding.progressBar)

                    // handle error
//                    fullScreenAlertDialog.dismissdialog()
                    showToast( "Image Upload Problem wait some Time Later")
                    Log.e("TAG = ", "onError: " + error.errorBody)
                    Log.e("TAG = ", "onError: " + error.message)
                    Log.e("TAG = ", "onError: $error")
                }
            })



    }



    override fun layoutRes(): Int =R.layout.activity_visitor_in

    override fun onPickResult(r: PickResult?) {  if (r!!.error == null) {
        dataBinding.userPhotoIV.setImageURI(r.uri)
        parcelPictureAsFile = File(r.uri.toString(), "${System.currentTimeMillis()}")
        logThis("fileURI ${parcelPictureAsFile!!.toURI()}  &  fileName ${parcelPictureAsFile!!.name}")
    } else Toast.makeText(this, r.error.message, Toast.LENGTH_LONG).show()


    }
}