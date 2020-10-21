package com.rokkhi.receptionistofficeapp.ui.profile

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityEditProfileBinding
import com.rokkhi.receptionistofficeapp.util.StaticFunction
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.listeners.IPickResult

class EditProfileActivity : BaseActivity<ActivityEditProfileBinding>(), IPickResult {

    lateinit var viewModel: EditProfileViewModel
    var mFileUri = ""
    private var bitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditProfileViewModel::class.java)

        dataBinding.userPhotoIV.setOnClickListener { StaticFunction.selectImage(this) }
        dataBinding.imageUploadTV.setOnClickListener { StaticFunction.selectImage(this) }

        dataBinding.phoneNoET.setText(StaticFunction.getvalidphone(mAuth.currentUser?.phoneNumber.toString()))

        dataBinding.SubmitUserInfoBtn.setOnClickListener { callSubmitDataFunction() }


    }

    private fun callSubmitDataFunction() {


        if (!StaticFunction.checkValidation(dataBinding.userNameET, dataBinding.userNameET.text, "আপনার নাম দিন !")) {
            return
        }
        if (!StaticFunction.checkValidation(dataBinding.emailET, dataBinding.emailET.text, "আপনার ইমেইল দিন !")) {
            return
        }
        if (!StaticFunction.isValidEmail(dataBinding.emailET.text.toString())) {

            dataBinding.emailET.error = "সঠিক ইমেল ব্যাবহার করুন !"
            dataBinding.emailET.requestFocus()

            return
        }

        if (!StaticFunction.checkValidation(dataBinding.addressET, dataBinding.addressET.text, "আপনার ঠিকানা দিন !")) {
            return
        }



        showToast("ok submit")


    }

    override fun layoutRes(): Int = R.layout.activity_edit_profile

    override fun onPickResult(r: PickResult?) {
        if (r!!.error == null) {
            dataBinding.userPhotoIV.setImageURI(null)
            mFileUri = r.uri.toString()
            bitmap = r.bitmap
            dataBinding.userPhotoIV.setImageURI(r.uri)
        } else {
            Toast.makeText(this, r.error.message, Toast.LENGTH_LONG).show()
        }
    }
}