package com.rokkhi.receptionistofficeapp.ui.parcel_delivery

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityParcelListBinding
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetParcelsData
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import com.rokkhi.receptionistofficeapp.util.StaticFunction

class ParcelDeliveredActivity : BaseActivity<ActivityParcelListBinding>(), AdapterParcelDelivered.OnAdapterItemClickListener {

    lateinit var viewModel: ParcelDeliveryViewModel
    override fun layoutRes(): Int = R.layout.activity_parcel_list
    private lateinit var adapter: AdapterParcelDelivered

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(ParcelDeliveryViewModel::class.java)
        createAdapter()
        getParcelDataFromServer()
    }


    //these are click listener as example/test [adapter interface]
    override fun onItemClick(parcelsData: GetParcelsData) {

        val builder = simpleAlertDialogBuilder("Delivery Alert !", "Are send this Parcel to user ?", true)
        builder.setPositiveButton("Yes") { dialog, _ ->

            markAsDeliveredParcel(parcelsData)

        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()


    }


    private fun simpleAlertDialogBuilder(title: String, body: String, cancelable: Boolean): AlertDialog.Builder = AlertDialog.Builder(activityContext!!).setTitle(title).setMessage(body).setCancelable(cancelable)



    private fun markAsDeliveredParcel(parcelsData: GetParcelsData) {


        viewModel.markParcelAsReceived(
            requesterProfileId = "",
            limit = "",
            pageId = "",
            companyId = sharedPrefHelper.getString(KeyFrame.COMPANY_ID),
            parcelId = parcelsData.id.toString()
        ).observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> StaticFunction.showSuccessAlert(activityContext)
                is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar)
                is ApiResponse.Failure -> logThisWithToast(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.toString())
            }
        })


    }

    private fun getParcelDataFromServer() {
        viewModel.getParcels(
            requesterProfileId = 0,
            limit = "",
            pageId = "",
            companyId = sharedPrefHelper.getString(KeyFrame.COMPANY_ID).toInt(),
            departmentId = sharedPrefHelper.getString(KeyFrame.DEPARTMENT_ID).toInt(),
            branchId = sharedPrefHelper.getString(KeyFrame.BRANCH_ID).toInt(),
            fromDate = "",
            toDate = ""
        ).observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> adapter.setListToAdapter(it.data.data as ArrayList<GetParcelsData>)
                is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar)
                is ApiResponse.Failure -> logThisWithToast(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.toString())
            }
        })
    }

    private fun createAdapter() {
        adapter = AdapterParcelDelivered()
        dataBinding.recyclerview.layoutManager = LinearLayoutManager(this)
        dataBinding.recyclerview.setHasFixedSize(true)
        dataBinding.recyclerview.adapter = adapter
        adapter.setOnAdapterItemClickListener(this)
    }
}