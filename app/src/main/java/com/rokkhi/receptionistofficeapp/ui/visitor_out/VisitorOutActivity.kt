package com.rokkhi.receptionistofficeapp.ui.visitor_out

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.base.BaseActivity
import com.rokkhi.receptionistofficeapp.databinding.ActivityVisitorListBinding
import com.rokkhi.receptionistofficeapp.network.wrapper.ApiResponse
import com.rokkhi.receptionistofficeapp.networkmodel.GetVisitorsData

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
        showMessage("clicked on item ${visitorData.id}")
        if (visitorData.status == "OUT") changeVisitorsStatus(visitorData.id, "IN")
        if (visitorData.status == "IN") changeVisitorsStatus(visitorData.id, "OUT")
    }

    private fun changeVisitorsStatus(visitorID: Int, statusMessage: String) {
        viewModel.changeVisitorOutStatus(visitorID, statusMessage).observe(this, Observer {
            when (it) {
                is ApiResponse.Success -> {
                    showMessage(it.data.status)
                    getVisitorsListDataFromServer()
                }
                is ApiResponse.Progress -> showProgressBar(it.loading, dataBinding.progressBar)
                is ApiResponse.Failure -> logThisWithToast(it.errorMessage.message)
                is ApiResponse.ErrorCode -> logThis(it.errorCode.toString())
            }
        })
    }

    private fun getVisitorsListDataFromServer() {
        viewModel.getVisitors().observe(this, Observer {
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