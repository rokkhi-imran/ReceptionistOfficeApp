package com.rokkhi.receptionistofficeapp.databiding

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.rokkhi.receptionistofficeapp.R
import com.rokkhi.receptionistofficeapp.helper.DateUtils
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import com.squareup.picasso.Picasso
import timber.log.Timber

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadProfileImage")
    fun loadProfileImage(view: ImageView, imageUrl: String) {
        if (imageUrl.isNotEmpty() && imageUrl.startsWith("http")) {
            Timber.e("leadingImageURL: $imageUrl into viewID: ${view.id}")
            Picasso.get().load(imageUrl).placeholder(R.drawable.user_avatar).centerCrop().resize(300, 300).into(view)
        }
    }
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadPracelImage(view: ImageView, imageUrl: String) {
        if (imageUrl.isNotEmpty() && imageUrl.startsWith("http")) {
            Timber.e("leadingImageURL: $imageUrl into viewID: ${view.id} ")
            Picasso.get().load(imageUrl).error(R.drawable.parcel_icon).placeholder(R.drawable.parcel_icon).centerCrop().resize(300, 300).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("convertDateDMY")
    fun convertDateDMY(view: TextView, date: String) {
        if (date.isNotEmpty()) view.text = DateUtils.convertReadableDateDMY(date)
    }

    @JvmStatic
    @BindingAdapter("layout_visibility")
    fun layoutVisibility(viewGroup: ViewGroup, status: Boolean) {
        if (status) viewGroup.visibility = View.VISIBLE else viewGroup.visibility = View.GONE
    }

    @JvmStatic
    @BindingAdapter("single_layout_visibility")
    fun viewVisibility(view: View, status: Boolean) {
        if (status) view.visibility = View.VISIBLE else view.visibility = View.GONE
    }

    @JvmStatic
    @BindingAdapter("millisToGetDate")
    fun getDateTimeFromMillis(view: TextView, millisToGetDate: Any) {
        val date: String = DateUtils.getDateTime(millisToGetDate.toString().toLong(), KeyFrame.DATE_TIME_PATTERN_2)
        view.text = date
    }
}