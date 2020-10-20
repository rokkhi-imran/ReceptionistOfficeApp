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

       dataBinding.lifecycleOwner=this;

        viewModel= ViewModelProvider(this, viewModelFactory).get(VisitorInViewModel::class.java)

        dataBinding.userPhotoIV.setOnClickListener { StaticFunction.selectImage(this) }

        dataBinding.imageUploadTV.setOnClickListener { StaticFunction.selectImage(this) }



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