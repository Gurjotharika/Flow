package com.gwtf.flow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gwtf.flow.Database.SqlDatabase
import com.gwtf.flow.Utilites.AmountCalculator
import com.gwtf.flow.adapter.DataAdapter
import com.gwtf.flow.model.DataModel

class BookActivity : AppCompatActivity() {

    lateinit var BookId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        BookId = intent.getStringExtra("id").toString()

        val txtIn = findViewById<TextView>(R.id.txt_incash)
        val txtOut = findViewById<TextView>(R.id.txt_outcash)
        val txtTotal = findViewById<TextView>(R.id.txt_total)

        txtIn.text = "" + AmountCalculator.getBookIn(this, BookId)
        txtOut.text = "" + AmountCalculator.getBookOut(this, BookId)
        txtTotal.text = "" + (AmountCalculator.getBookIn(this, BookId) + AmountCalculator.getBookIn(this, BookId))

        val btnCashIn = findViewById<Button>(R.id.btnCashIn)
        val btnCashOut = findViewById<Button>(R.id.btnCashOut)
        btnCashIn.setOnClickListener {
            startActivity(Intent(this, CashActivity::class.java)
                .putExtra("id", BookId).putExtra("cash", true))
        }

        btnCashOut.setOnClickListener {
            startActivity(Intent(this, CashActivity::class.java)
                .putExtra("id", BookId).putExtra("cash", false))
        }

        val db = SqlDatabase(this)
        val list_payments = findViewById<RecyclerView>(R.id.list_payments)
        val totalBooks = findViewById<TextView>(R.id.totalBooks)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        list_payments.layoutManager = layoutManager
        var list = ArrayList<DataModel>()
        list = db.getData(BookId)
        val adadpter = DataAdapter(list)
        totalBooks.text = "Showing " + list.size + " entries"
        list_payments.adapter = adadpter

    }
}