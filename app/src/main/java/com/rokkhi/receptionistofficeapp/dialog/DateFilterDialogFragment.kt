package live.qtec.survey.dialog
/*

import android.os.Bundle
import com.rokkhi.receptionistofficeapp.R


class DateFilterDialogFragment : BaseDialog<DialogDateFilterBinding>(){
    override fun layoutRes(): Int = R.layout.dialog_date_filter
    companion object {
        val instance: DateFilterDialogFragment by lazy { DateFilterDialogFragment() }
    }

    private lateinit var listener: DateFilterSubmitListener
    private lateinit var fromDate:String
    private lateinit var toDate:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        dataBinding.fromDateEt.keyListener = null
//        dataBinding.toDateEt.keyListener = null
//
//        dataBinding.submitBtn.setOnClickListener{
//            if (dataBinding.fromDateEt.text.toString().isEmpty() || dataBinding.toDateEt.text.toString().isEmpty()){
//                Toast.makeText(applicationContext,"Please provide valid date",Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            listener.onClickDateFilterSubmit(fromDate,toDate,dataBinding.fromDateEt.text.toString(),dataBinding.toDateEt.text.toString())
//            dialog?.dismiss()
//        }
//        dataBinding.fromDateEt.setOnClickListener {showDatePickerDialog(dataBinding.fromDateEt)}
//        dataBinding.toDateEt.setOnClickListener {showDatePickerDialog(dataBinding.toDateEt)}

    }
//    private fun showDatePickerDialog(view:EditText) {
//        val calendar = Calendar.getInstance()
//        DatePickerDialog(activityContext!!,R.style.DatePickerDialogTheme,
//            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
//                view.setText(DateUtils.convertReadableDateDMY("$dayOfMonth-${monthOfYear+1}-$year"))
//                val day = if ("$dayOfMonth".length == 1) "0$dayOfMonth" else "$dayOfMonth"
//                val month = if ("${monthOfYear+1}".length == 1) "0${monthOfYear+1}" else "${monthOfYear+1}"
//                if (view == dataBinding.fromDateEt) fromDate = "$year-$month-$day"
//                else toDate = "$year-$month-$day"
//            }, calendar.get(Calendar.YEAR),  calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
//    }

    interface DateFilterSubmitListener{
        fun onClickDateFilterSubmit(serverFromDate:String,serverToDate:String,readableFromDate:String,readableToDate:String)
    }
    fun setDateFilterSubmitListener(listener: DateFilterSubmitListener){
        this.listener = listener
    }

}
*/
