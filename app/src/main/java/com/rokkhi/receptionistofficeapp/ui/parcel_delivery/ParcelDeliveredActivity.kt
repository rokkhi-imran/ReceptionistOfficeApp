package com.rokkhi.receptionistofficeapp.ui.parcel_delivery

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityParcelListBinding
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetParcelsData

class ParcelDeliveredActivity : BaseActivity<ActivityParcelListBinding>(), AdapterParcelDelivered.OnAdapterItemClickListener {

    lateinit var viewModel: ParcelDeliveryViewModel
    override fun layoutRes(): Int = R.layout.activity_parcel_list
    private lateinit var adapter: AdapterParcelDelivered

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(ParcelDeliveryViewModel::class.java)
        createAdapter()
        getParcelDataFromServer(2) //todo: change companyID [ use sharedPrefHelper.getString(KeyFrame.COMPANY_ID) ]
    }


    //these are click listener as example/test [adapter interface]
    override fun onItemClick(parcelsData: GetParcelsData) {
        showMessage("item clicked on titleImageURL\n${parcelsData.image}")
    }

    //these are click listener as example/test [adapter interface]
    override fun onButtonClick(parcelsData: GetParcelsData) {
        showMessage("Parcel Status = ${parcelsData.status}")
    }

    private fun getParcelDataFromServer(companyID: Int) {
        viewModel.getParcels(companyID).observe(this, Observer {
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