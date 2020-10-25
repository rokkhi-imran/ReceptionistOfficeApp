package com.rokkhi.receptionistofficeapp.ui.attendance_out

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.adapter.AdapterEmployeeList
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityAttendanceOutBinding
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeListData
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import com.rokkhi.receptionistofficeapp.util.StaticFunction

class AttendanceOutActivity : BaseActivity<ActivityAttendanceOutBinding>(), AdapterEmployeeList.OnAdapterItemClickListener {

    lateinit var viewModel: AttendanceOutViewModel


    private lateinit var adapterEmployeeList: AdapterEmployeeList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(AttendanceOutViewModel::class.java)



        adapterEmployeeList = AdapterEmployeeList()
        dataBinding.recyclerview.layoutManager = LinearLayoutManager(this)
        dataBinding.recyclerview.setHasFixedSize(true)
        dataBinding.recyclerview.adapter = adapterEmployeeList
        adapterEmployeeList.setOnAdapterItemClickListener(this)


        viewModel.getEmployeeList(
            sharedPrefHelper.getString(KeyFrame.USER_ID).toInt(), "", "",
            sharedPrefHelper.getString(KeyFrame.COMPANY_ID).toInt(), sharedPrefHelper.getString(KeyFrame.BRANCH_ID).toString(),
            "", "", ""
        ).observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> adapterEmployeeList.setListToAdapter(it.data.data as ArrayList<EmployeeListData>)
                is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar2)
                is ApiResponse.Failure -> logThisWithToast(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.toString())
            }
        })

    }

    override fun layoutRes(): Int = R.layout.activity_attendance_out


    override fun onItemClick(employeeData: EmployeeListData) {

        val builder = simpleAlertDialogBuilder("Exit Alert !", "Do you want Exit This User ?", true)
        builder.setPositiveButton("Yes") { dialog, _ ->

            viewModel.recordEmployeeAttendanceOut(
                requesterProfileId = employeeData.id.toInt(),
                limit = "",
                pageId = "",
                companyId = sharedPrefHelper.getString(KeyFrame.COMPANY_ID).toInt(),
                employeeId = employeeData.id.toString(),
                departmentId = employeeData.department.id.toString(),
                branchId = employeeData.branch.id.toString(),
                receptionistId = "",//Receptionist EmployeeID
                acknowledgedBy = employeeData.id.toString(),
                status = KeyFrame.KEY_OUTSIDE,
                associatedLoggedinDeviceId = sharedPrefHelper.getString(KeyFrame.USER_ID)

            ).observe(this, Observer {
                when (it) {
                    is ApiResponse.Success -> StaticFunction.showSuccessAlert(activityContext)
                    is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar2)
                    is ApiResponse.Failure -> logThisWithToast(it.errorMessage.message)
                    is ApiResponse.ErrorCode -> logThis(it.errorCode.toString())
                }
            })


            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()

    }

    private fun simpleAlertDialogBuilder(title: String, body: String, cancelable: Boolean): AlertDialog.Builder = AlertDialog.Builder(activityContext!!).setTitle(title).setMessage(body).setCancelable(cancelable)


}