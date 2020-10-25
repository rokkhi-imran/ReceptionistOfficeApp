package com.rokkhi.receptionistofficeapp.ui.visitor_out

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityVisitorListBinding
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetVisitorsData
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import com.rokkhi.receptionistofficeapp.util.StaticFunction

class VisitorOutActivity : BaseActivity<ActivityVisitorListBinding>(), AdapterVisitorOut.OnAdapterItemClickListener {

    lateinit var viewModel: VisitorOutViewModel
    override fun layoutRes(): Int = R.layout.activity_visitor_list
    private lateinit var adapter: AdapterVisitorOut

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(VisitorOutViewModel::class.java)

        createAdapter()
        getVisitorsListDataFromServer()
    }

    override fun onItemClick(visitorData: GetVisitorsData) {

        val builder = simpleAlertDialogBuilder("Exit Alert !", "Do you want Exit This User ?", true)
        builder.setPositiveButton("Yes") { dialog, _ ->

      changeVisitorsStatus(visitorData)


            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()

    }

    private fun simpleAlertDialogBuilder(title: String, body: String, cancelable: Boolean): AlertDialog.Builder = AlertDialog.Builder(activityContext!!).setTitle(title).setMessage(body).setCancelable(cancelable)


    private fun changeVisitorsStatus(visitorID: GetVisitorsData) {


        viewModel.changeVisitorOutStatus(
            requesterProfileId = 0,
            limit = "",
            pageId = "",
            companyId = sharedPrefHelper.getString(KeyFrame.COMPANY_ID).toInt(),
            visitorId = visitorID.id,
            newStatus = KeyFrame.KEY_VISITOR_OUTSIDE,
            associatedLoggedinDeviceId = sharedPrefHelper.getString(KeyFrame.USER_ID)
        ).observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> {
                    showMessage(it.data.status)
                    getVisitorsListDataFromServer()
                }
                is ApiResponse.Progress -> StaticFunction.showSuccessAlert(activityContext)
                is ApiResponse.Failure -> logThisWithToast(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.toString())
            }
        })
    }

    private fun getVisitorsListDataFromServer() {

        viewModel.getVisitors(
            requesterProfileId = 0,
            limit = "",
            pageId = "",
            companyId = sharedPrefHelper.getString(KeyFrame.COMPANY_ID).toInt(),
            departmentId = sharedPrefHelper.getString(KeyFrame.DEPARTMENT_ID).toInt(),
            branchId = sharedPrefHelper.getString(KeyFrame.BRANCH_ID).toInt(),
            status = KeyFrame.KEY_VISITOR_INSIDE,
            fromDate = "",
            toDate = "").observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> adapter.setListToAdapter(it.data.data)
                is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar)
                is ApiResponse.Failure -> logThisWithToast(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.toString())
            }
        })
    }

    private fun createAdapter() {
        adapter = AdapterVisitorOut()
        dataBinding.recyclerview.layoutManager = LinearLayoutManager(this)
        dataBinding.recyclerview.setHasFixedSize(true)
        dataBinding.recyclerview.adapter = adapter
        adapter.setOnAdapterItemClickListener(this)
    }



}