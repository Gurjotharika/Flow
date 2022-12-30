package com.gwtf.flow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gwtf.flow.Database.SqlDatabase
import com.gwtf.flow.adapter.BusinessCategoryAdapter
import com.gwtf.flow.adapter.BusinessTypeAdapter
import com.gwtf.flow.model.CategoriesModel

class SelectBusinessTypeActivity : AppCompatActivity() {

    companion object {
        lateinit var listBusiness: RecyclerView
        var isSelected = false
        var type: String = ""
        lateinit var btn_submit: Button
        lateinit var database: SqlDatabase
        var list = ArrayList<CategoriesModel>()

        fun getData() {
            list.clear()
            list.add(CategoriesModel("", "Retailer"))
            list.add(CategoriesModel("", "Distributor"))
            list.add(CategoriesModel("", "Manufacturer"))
            list.add(CategoriesModel("", "Service Provider"))
            list.add(CategoriesModel("", "Trader"))
            list.add(CategoriesModel("", "Other"))
            val adadper = BusinessTypeAdapter(list)
            listBusiness.adapter = adadper
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_business_type)
        database = SqlDatabase(this)

        val BusinessId = intent.getStringExtra("Id")
        val category = intent.getStringExtra("category")

        val btnSkip = findViewById<TextView>(R.id.btnSkip)
        btn_submit = findViewById(R.id.btn_submit)

        btnSkip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        listBusiness = findViewById(R.id.listBusiness)
        val lmanager = GridLayoutManager(this, 1)
        lmanager.orientation = LinearLayoutManager.VERTICAL
        listBusiness.layoutManager = lmanager
        getData()

        btn_submit.setOnClickListener {
            if (isSelected) {
                database.updateBusinessCategoryTYpe(BusinessId,
                    category , type)
                startActivity(
                    Intent(this, MainActivity::class.java)
                )
            }
        }
    }
}