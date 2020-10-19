package com.rokkhi.receptionistofficeapp.ui.visitor_out

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityVisitorListBinding
import com.rokkhi.receptionistofficeapp.ui.visitor_in.VisitorInViewModel

class VisitorListActivity : BaseActivity<ActivityVisitorListBinding>() {

    lateinit var viewModel: VisitorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner=this
        viewModel= ViewModelProvider(this,viewModelFactory).get(VisitorListViewModel::class.java)


    }

    override fun layoutRes(): Int =R.layout.activity_visitor_list
}