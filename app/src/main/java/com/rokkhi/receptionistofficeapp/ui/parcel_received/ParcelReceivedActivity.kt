package com.rokkhi.receptionistofficeapp.ui.parcel_received

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityParcelInBinding
import com.rokkhi.receptionistofficeapp.util.StaticFunction
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_main.*

class ParcelReceivedActivity : BaseActivity<ActivityParcelInBinding>(), IPickResult {

    lateinit var viewModel: ParcelReceivedViewModel
    var mFileUri = ""
    private var bitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.lifecycleOwner = this
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ParcelReceivedViewModel::class.java);

        dataBinding.userPhotoIV.setOnClickListener { StaticFunction.selectImage(this) }
        dataBinding.imageUploadTV.setOnClickListener { StaticFunction.selectImage(this) }

    }

    override fun layoutRes(): Int = R.layout.activity_parcel_in

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