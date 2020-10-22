package com.rokkhi.receptionistofficeapp.ui.attendance_in

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityAttendanceBinding

class AttendanceInActivity : BaseActivity<ActivityAttendanceBinding>() {
    lateinit var viewModel: AttendanceInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.lifecycleOwner=this
        viewModel=ViewModelProvider(this,viewModelFactory).get(AttendanceInViewModel::class.java)




    }

    override fun layoutRes(): Int =R.layout.activity_attendance;
}