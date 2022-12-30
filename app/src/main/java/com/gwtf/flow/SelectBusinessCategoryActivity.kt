package com.gwtf.flow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.api.Distribution.BucketOptions.Linear
import com.gwtf.flow.Database.SqlDatabase
import com.gwtf.flow.adapter.BusinessCategoryAdapter
import com.gwtf.flow.model.CategoriesModel

class SelectBusinessCategoryActivity : AppCompatActivity() {

    companion object {
        lateinit var listBusiness: RecyclerView
        var isSelected = false
        var type: String = ""
        lateinit var btn_submit: Button
        lateinit var database: SqlDatabase
        var list = ArrayList<CategoriesModel>()

        fun getData() {
            list.clear()
            list.add(CategoriesModel("", "Agriculture"))
            list.add(CategoriesModel("", "Construction"))
            list.add(CategoriesModel("", "Education"))
            list.add(CategoriesModel("", "Electronics"))
            list.add(CategoriesModel("", "Financial\nServices"))
            list.add(CategoriesModel("", "Food\nRestaurant"))
            list.add(CategoriesModel("", "Clothes\nFashion"))
            list.add(CategoriesModel("", "Hardware"))
            list.add(CategoriesModel("", "Jewellery"))
            list.add(CategoriesModel("", "Healthcare &\n Fitness"))
            list.add(CategoriesModel("", "Kirana\n/Grocery"))
            list.add(CategoriesModel("", "Transport"))
            list.add(CategoriesModel("", "Other"))
            val adadper = BusinessCategoryAdapter(list)
            listBusiness.adapter = adadper
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_business_category)
        database = SqlDatabase(this)

        val BusinessId = intent.getStringExtra("BusinessId")

        val btnSkip = findViewById<TextView>(R.id.btnSkip)
        btn_submit = findViewById(R.id.btn_submit)

        btnSkip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        listBusiness = findViewById(R.id.listBusiness)
        val lmanager = GridLayoutManager(this, 2)
        lmanager.orientation = LinearLayoutManager.VERTICAL
        listBusiness.layoutManager = lmanager
        getData()

        btn_submit.setOnClickListener {
            if (isSelected) {
                database.updateBusinessCategoryTYpe(BusinessId, type, "")
                startActivity(Intent(this, SelectBusinessTypeActivity::class.java)
                    .putExtra("Id", BusinessId)
                    .putExtra("category", type)
                )
            }
        }

    }
}