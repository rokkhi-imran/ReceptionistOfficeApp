package com.rokkhi.receptionistofficeapp.ui.parcel_received

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityParcelInBinding
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.statics.EmployeeEntryStatuss
import com.rokkhi.receptionistofficeapp.util.StaticFunction
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.listeners.IPickResult

class ParcelReceivedActivity : BaseActivity<ActivityParcelInBinding>(), IPickResult {

    lateinit var viewModel: ParcelReceivedViewModel
    var mFileUri = ""
    private var parcelPicture: Bitmap? = null

    override fun layoutRes(): Int = R.layout.activity_parcel_in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(ParcelReceivedViewModel::class.java)

        dataBinding.userPhotoIV.setOnClickListener { StaticFunction.selectImage(this) }
        dataBinding.imageUploadTV.setOnClickListener { StaticFunction.selectImage(this) }
        dataBinding.SubmitUserInfoBtn.setOnClickListener {
            if (checkInputValidation()) {
                viewModel.uploadSingle(parcelPicture!!, EmployeeEntryStatuss.INSIDE.status, "", "").observe(this, Observer { apiResponse -> // todo: remove this line
                    when (apiResponse) {
                        is ApiResponse.Success -> {
                            showMessage(apiResponse.data.imageDownloadURL)
                        }
                        is ApiResponse.Progress -> showProgressBar(apiResponse.loading, dataBinding.progressBar)
                        is ApiResponse.Failure -> showMessage(apiResponse.errorMessage.message)
                    }
                })
            }
        }
    }

    private fun checkInputValidation(): Boolean {
        if (!StaticFunction.checkValidation(dataBinding.parcelNameET, dataBinding.parcelNameET.text, "পার্সেল নাম ? ")) return false
        if (!StaticFunction.checkValidation(dataBinding.parcelCompanyET, dataBinding.parcelCompanyET.text, "কৈ থেকে এসেছে ? ")) return false
        if (!StaticFunction.checkValidation(dataBinding.whereParcelCame, dataBinding.whereParcelCame.text, "কার কাছে এসেছে ? ")) return false
        return true
    }

    override fun onPickResult(r: PickResult?) {
        if (r!!.error == null) {
            dataBinding.userPhotoIV.setImageURI(null)
            mFileUri = r.uri.toString()
            parcelPicture = r.bitmap
            dataBinding.userPhotoIV.setImageURI(r.uri)
        } else Toast.makeText(this, r.error.message, Toast.LENGTH_LONG).show()
    }
}