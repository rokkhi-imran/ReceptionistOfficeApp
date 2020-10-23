package com.rokkhi.receptionistofficeapp.ui.visitor_out

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.databinding.ItemVisBinding
import com.rokkhi.receptionistofficeapp.networkmodel.GetVisitorsData

class AdapterVisitorOut : RecyclerView.Adapter<AdapterVisitorOut.ViewHolder>() {

    //------------------------ do not alter ------------------------
    private var mArray = ArrayList<GetVisitorsData>()
    private var listener: OnAdapterItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_vis, parent, false))
    override fun getItemCount(): Int = mArray.size
    fun setOnAdapterItemClickListener(adapterItemClickListener: OnAdapterItemClickListener) {
        this.listener = adapterItemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = mArray[position]
    }

    fun setListToAdapter(mArray: List<GetVisitorsData>) {
        this.mArray = mArray as ArrayList<GetVisitorsData>
        notifyDataSetChanged()
    }
    //------------------------ do not alter ------------------------

    inner class ViewHolder(val binding: ItemVisBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { listener?.onItemClick(mArray[adapterPosition]) }
        }
    }

    interface OnAdapterItemClickListener {
        fun onItemClick(parcelsData: GetVisitorsData)
    }

}
