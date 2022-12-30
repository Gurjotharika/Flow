package com.gwtf.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gwtf.flow.Database.SqlDatabase
import com.gwtf.flow.adapter.BooksReportAdapter
import com.gwtf.flow.model.DataModel

class FindBookActivity : AppCompatActivity() {

    var list = ArrayList<DataModel>()
    lateinit var adapter: BooksReportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_book)
        val db = SqlDatabase(this)

    }
}