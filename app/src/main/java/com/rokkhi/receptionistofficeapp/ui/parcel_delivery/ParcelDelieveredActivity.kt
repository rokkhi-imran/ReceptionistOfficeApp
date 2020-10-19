package com.rokkhi.receptionistofficeapp.ui.parcel_delivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityParcelListBinding

class ParcelDelieveredActivity : BaseActivity<ActivityParcelListBinding>() {
    lateinit var viewModel: ParcelDeliveryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner=this
        viewModel=ViewModelProvider(this,viewModelFactory).get(ParcelDeliveryViewModel::class.java)



    }

    override fun layoutRes(): Int =R.layout.activity_parcel_list
}