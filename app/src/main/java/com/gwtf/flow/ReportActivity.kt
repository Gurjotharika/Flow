package com.gwtf.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gwtf.flow.Database.SqlDatabase
import com.gwtf.flow.Utilites.AmountCalculator
import com.gwtf.flow.adapter.BooksReportAdapter
import com.gwtf.flow.model.DataModel

class ReportActivity : AppCompatActivity() {

    var list = ArrayList<DataModel>()
    lateinit var adapter: BooksReportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        val db = SqlDatabase(this)

        val totalOut = findViewById<TextView>(R.id.totalOut)
        val totalEnteries = findViewById<TextView>(R.id.totalEnteries)
        totalOut.text = "" + AmountCalculator.getOut(this)
        val totalIn = findViewById<TextView>(R.id.totalIn)
        totalIn.text = "" + AmountCalculator.getIn(this)

        val listReports = findViewById<RecyclerView>(R.id.listReports)
        listReports.isNestedScrollingEnabled = false
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        listReports.layoutManager = layoutManager

        list = db.getData()
        adapter = BooksReportAdapter(list)
        listReports.adapter = adapter

        totalEnteries.text = "" + list.size + " Entries"

        val searchTxt = findViewById<EditText>(R.id.search_text)
        searchTxt.doOnTextChanged { text, start, before, count ->
            filter(searchTxt.text.toString())
        }

    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<DataModel> = ArrayList()
        for (item in list) {
            if (item.partyName.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredlist)
        }
    }

}