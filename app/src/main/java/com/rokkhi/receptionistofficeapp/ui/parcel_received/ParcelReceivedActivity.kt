package com.rokkhi.receptionistofficeapp.ui.parcel_received

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityParcelInBinding
import kotlinx.android.synthetic.main.activity_main.*

class ParcelReceivedActivity : BaseActivity<ActivityParcelInBinding>() {
    lateinit var viewModel: ParcelReceivedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.lifecycleOwner=this
        viewModel=ViewModelProvider(this,viewModelFactory).get(ParcelReceivedViewModel::class.java);

    }

    override fun layoutRes(): Int =R.layout.activity_parcel_in
}