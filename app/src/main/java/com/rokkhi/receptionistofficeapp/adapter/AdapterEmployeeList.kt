package com.rokkhi.receptionistofficeapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.databinding.ItemEmployeeBinding
import com.rokkhi.receptionistofficeapp.networkmodel.EmployeeListData


class AdapterEmployeeList : RecyclerView.Adapter<AdapterEmployeeList.ViewHolder>() {

    //------------------------ do not alter ------------------------
    private var mArray = ArrayList<EmployeeListData>()
    private var listener: OnAdapterItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_employee, parent, false))
    override fun getItemCount(): Int = mArray.size
    fun setOnAdapterItemClickListener(adapterItemClickListener: OnAdapterItemClickListener) {
        this.listener = adapterItemClickListener
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = mArray[position]
    }
    fun setListToAdapter(mArray: ArrayList<EmployeeListData>) {
        this.mArray = mArray
        notifyDataSetChanged()
    }
    //------------------------ do not alter ------------------------

    inner class ViewHolder(val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

            Log.e("TAG", ": fsdajgkflhf sjk fhjksdh gjksfdhg jkfdhg jkdfhjkgh fdfjkghjkdf hgjkdfhgjkhdgjkdsh jkgd ==  ${mArray.size} $adapterPosition  ", )
            /* click events and view init code goes here, changing any view property should go here
               mArray[adapterPosition] holds the current item object in this scope. so any property
               can directly altered or re-init will change effect.
            */
            binding.root.setOnClickListener { listener?.onItemClick(mArray[adapterPosition]) }

        }
    }


    interface OnAdapterItemClickListener {
        fun onItemClick(parcelsData: EmployeeListData)

    }

}
