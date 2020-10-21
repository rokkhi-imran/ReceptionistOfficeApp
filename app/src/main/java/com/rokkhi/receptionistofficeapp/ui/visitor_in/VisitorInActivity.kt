package com.rokkhi.receptionistofficeapp.ui.visitor_in

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityVisitorInBinding
import com.rokkhi.receptionistofficeapp.util.StaticFunction
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.listeners.IPickResult

class VisitorInActivity : BaseActivity<ActivityVisitorInBinding>() ,IPickResult{

    lateinit var viewModel: VisitorInViewModel
    var mFileUri = ""
    private var bitmap: Bitmap? = null

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
        showToast("Validation Done")


    }

    override fun layoutRes(): Int =R.layout.activity_visitor_in

    override fun onPickResult(r: PickResult?) {

        if (r!!.error == null) {
            dataBinding.userPhotoIV.setImageURI(null)
            mFileUri = r!!.uri.toString()
            bitmap = r!!.bitmap
            dataBinding.userPhotoIV.setImageURI(r.uri)
        } else {
            Toast.makeText(this, r!!.error.message, Toast.LENGTH_LONG).show()
        }


    }
}