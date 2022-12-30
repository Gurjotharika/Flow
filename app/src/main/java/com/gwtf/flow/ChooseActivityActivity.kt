package com.gwtf.flow

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gwtf.flow.Database.SqlDatabase
import com.gwtf.flow.R
import com.gwtf.flow.adapter.CategoryAdapter
import com.gwtf.flow.adapter.PartyAdapter
import com.gwtf.flow.model.CategoriesModel
import com.gwtf.flow.model.PartyModel

class ChooseActivityActivity : AppCompatActivity() {

    companion object {
        var categoy: String = ""
        var id: String = ""
        var isSelected = false

        lateinit var list_categories: RecyclerView
        lateinit var database: SqlDatabase
        lateinit var doneSelected: TextView
        var BookId = ""

        fun DoneButton() {
            if (isSelected) {
                doneSelected.setTextColor(Color.parseColor("#5dc7d6"))
                doneSelected.isEnabled = true
            } else {
                doneSelected.setTextColor(Color.parseColor("#505050"))
            }
        }

        fun getData() {
            var list = ArrayList<CategoriesModel>()
            list.clear()
            list = database.getCategories(BookId)
            var adapter = CategoryAdapter(list)
            list_categories.adapter = adapter
            DoneButton()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_activity)

        doneSelected = findViewById(R.id.doneSelected)
        BookId = intent.getStringExtra("BookId").toString()

        list_categories = findViewById(R.id.list_categories)
        database = SqlDatabase(this)

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        list_categories.layoutManager = layoutManager

        getData()

        doneSelected.setOnClickListener {
            if (isSelected) {
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        val intent8 = Intent().apply {
            putExtra("category", ChooseActivityActivity.categoy)
        }
        setResult(Activity.RESULT_OK, intent8)
        super.onBackPressed()
    }
}