package com.rokkhi.receptionistofficeapp.ui.parcel_delivery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.databinding.ItemParcelBinding
import com.rokkhi.receptionistofficeapp.networkmodel.GetParcelsData

class AdapterParcelDelivered : RecyclerView.Adapter<AdapterParcelDelivered.ViewHolder>() {

    //------------------------ do not alter ------------------------
    private var mArray = ArrayList<GetParcelsData>()
    private var listener: OnAdapterItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_parcel, parent, false))
    override fun getItemCount(): Int = mArray.size
    fun setOnAdapterItemClickListener(adapterItemClickListener: OnAdapterItemClickListener) {
        this.listener = adapterItemClickListener
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = mArray[position]
    }
    fun setListToAdapter(mArray: ArrayList<GetParcelsData>) {
        this.mArray = mArray
        notifyDataSetChanged()
    }
    //------------------------ do not alter ------------------------

    inner class ViewHolder(val binding: ItemParcelBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            /* click events and view init code goes here, changing any view property should go here
               mArray[adapterPosition] holds the current item object in this scope. so any property
               can directly altered or re-init will change effect.
            */
            binding.root.setOnClickListener { listener?.onItemClick(mArray[adapterPosition]) }
            binding.btnStatus.setOnClickListener { listener?.onButtonClick(mArray[adapterPosition]) }
        }
    }


    interface OnAdapterItemClickListener {
        fun onItemClick(parcelsData: GetParcelsData)
        fun onButtonClick(parcelsData: GetParcelsData)
    }

}
