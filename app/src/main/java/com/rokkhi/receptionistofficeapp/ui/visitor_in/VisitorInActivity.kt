package com.rokkhi.receptionistofficeapp.ui.visitor_in

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityVisitorInBinding

class VisitorInActivity : BaseActivity<ActivityVisitorInBinding>() {

    lateinit var viewModel: VisitorInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       dataBinding.lifecycleOwner=this;

        viewModel= ViewModelProvider(this,viewModelFactory).get(VisitorInViewModel::class.java)



    }

    override fun layoutRes(): Int =R.layout.activity_visitor_in
}