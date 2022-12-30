package com.gwtf.flow.adapter

import android.content.Intent
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gwtf.flow.BookActivity
import com.gwtf.flow.Database.SqlDatabase
import com.gwtf.flow.DuplicateBookActivity
import com.gwtf.flow.EditBookNameActivity
import com.gwtf.flow.R
import com.gwtf.flow.Utilites.AmountCalculator
import com.gwtf.flow.Utilites.Constants.Business_Selected
import com.gwtf.flow.Utilites.IDGenrator
import com.gwtf.flow.Utilites.getDateTime
import com.gwtf.flow.model.BookModel
import com.gwtf.flow.model.BusinessModel
import com.gwtf.flow.model.DataModel
import org.checkerframework.common.subtyping.qual.Bottom
import java.sql.Time
import java.util.*
import java.util.stream.LongStream

class DataAdapter (private val LST: List<DataModel>):
 RecyclerView.Adapter<DataAdapter.ViewHolder>() {

     class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         fun bind (list: DataModel) {
             val title = itemView.findViewById<TextView>(R.id.title)
             val type = itemView.findViewById<TextView>(R.id.type)
             val payment = itemView.findViewById<TextView>(R.id.payment)
             val mode = itemView.findViewById<TextView>(R.id.mode)
             val remark = itemView.findViewById<TextView>(R.id.remark)
             val amount = itemView.findViewById<TextView>(R.id.amount)
             val time_text = itemView.findViewById<TextView>(R.id.time_text)

             time_text.text = "Date: " + list.date + " Time: " + list.time
             amount.text = list.amount
             remark.text = list.remark
             mode.text = list.paymentMode
             remark.text = list.remark
             payment.text = list.amount
             type.text = ""

             if (list.paymentType.equals("IN")) {
                 payment.setTextColor(Color.parseColor("#357A38"))
             } else {
                 payment.setTextColor(Color.parseColor("#B0372E"))
             }

         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_job, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder).bind(LST[position])
    }

    override fun getItemCount(): Int {
        return LST.size
    }

}

