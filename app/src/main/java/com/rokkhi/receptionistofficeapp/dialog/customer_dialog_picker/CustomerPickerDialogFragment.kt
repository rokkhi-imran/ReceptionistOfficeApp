package com.rokkhi.receptionistofficeapp.dialog.customer_dialog_picker
/*

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rokkhi.receptionistofficeapp.R

class CustomerPickerDialogFragment(private val list: ArrayList<String>) : BaseDialog<DialogPickCustomerBinding>(),
    CustomerPickerDialogAdapter.OnAdapterItemClickListener {
    override fun layoutRes(): Int = R.layout.dialog_pick_customer

    private lateinit var listener: CustomerSelectListener
    private lateinit var adapter: CustomerPickerDialogAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = CustomerPickerDialogAdapter()
        dataBinding.rvCustomerList.layoutManager = LinearLayoutManager(context)
        dataBinding.rvCustomerList.setHasFixedSize(true)
        dataBinding.rvCustomerList.adapter = adapter
        adapter.setOnAdapterItemClickListener(this)
        adapter.setCustomerList(list)

    }

    interface CustomerSelectListener {
        fun onCustomerSelected(selectedCustomer: String)
    }

    fun setOnCustomerSelected(listener: CustomerSelectListener) {
        this.listener = listener
    }

    override fun onItemClick(customer: String) {
        listener.onCustomerSelected(customer)
    }

}


@SuppressLint("SetTextI18n")
class CustomerPickerDialogAdapter : RecyclerView.Adapter<CustomerPickerDialogAdapter.CustomerViewHolder>() {

    private var customerList = ArrayList<String>()
    private var listener: OnAdapterItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view: ListItemCustomerPickerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_customer_picker, parent, false)
        return CustomerViewHolder(view)
    }

    override fun getItemCount(): Int = customerList.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
//        holder.binding.customer = customerList[position]
    }

    inner class CustomerViewHolder(val binding: ListItemCustomerPickerBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { listener?.onItemClick(customerList[adapterPosition]) }
        }
    }

    fun setCustomerList(customerList: ArrayList<String>) {
        this.customerList = customerList
        notifyDataSetChanged()
    }

    interface OnAdapterItemClickListener {
        fun onItemClick(customer: String)
    }

    fun setOnAdapterItemClickListener(adapterItemClickListener: OnAdapterItemClickListener) {
        this.listener = adapterItemClickListener
    }
}
*/
