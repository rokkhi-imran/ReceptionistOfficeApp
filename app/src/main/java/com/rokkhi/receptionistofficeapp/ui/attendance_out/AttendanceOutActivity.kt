package com.rokkhi.receptionistofficeapp.ui.attendance_out

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityAttendanceOutBinding

class AttendanceOutActivity : BaseActivity<ActivityAttendanceOutBinding>() {

    lateinit var viewModel: AttendanceOutViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.lifecycleOwner=this
        viewModel=ViewModelProvider(this,viewModelFactory).get(AttendanceOutViewModel::class.java)



    }

    override fun layoutRes(): Int =R.layout.activity_attendance_out
}