package com.gwtf.flow

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_ANSWER
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gwtf.flow.Database.SqlDatabase
import com.gwtf.flow.Utilites.IDGenrator
import com.gwtf.flow.Utilites.getDateTime
import com.gwtf.flow.adapter.ContactAdapter
import com.gwtf.flow.adapter.PartyAdapter
import com.gwtf.flow.model.ContactModel
import com.gwtf.flow.model.PartyModel

class ChoosePartyActivity : AppCompatActivity() {

    companion object {
        var name: String = ""
        var number: String = ""
        var isSelected = false
        lateinit var list_contacts: RecyclerView
        lateinit var database: SqlDatabase
        lateinit var list_parties: RecyclerView
        lateinit var textView2: TextView
        lateinit var doneSelected: TextView

        fun DoneButton() {
            if (isSelected) {
                doneSelected.setTextColor(Color.parseColor("#5dc7d6"))
                doneSelected.isEnabled = true
            } else {
                doneSelected.setTextColor(Color.parseColor("#505050"))
            }
        }

        fun getData() {
            var list = ArrayList<PartyModel>()
            list.clear()
            list = database.getParty()
            var adapter = PartyAdapter(list)
            textView2.text = "Added Parties (" + list.size + ")"
            list_parties.adapter = adapter
            DoneButton()
        }
    }


    val permissions = arrayOf(
        Manifest.permission.READ_CONTACTS
    )
    val permissionCode = 78

    override fun onBackPressed() {
        val intent8 = Intent().apply {
            putExtra("name", name)
            putExtra("number", number)
        }
        setResult(Activity.RESULT_OK, intent8)
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_party)
        database = SqlDatabase(this)
        val btn_addParty = findViewById<CardView>(R.id.btn_addParty)
        val btn_addparty = findViewById<LinearLayout>(R.id.btnaddparty)
        btn_addParty.setOnClickListener{
            showAddPartyDialog()
        }
        btn_addparty.setOnClickListener{
            showAddPartyDialog()
        }

        list_parties = findViewById<RecyclerView>(R.id.list_parties)
        doneSelected = findViewById(R.id.doneSelected)

        doneSelected.setOnClickListener {
            if(isSelected) {
                onBackPressed()
            }
        }

        textView2 = findViewById<TextView>(R.id.textView2)
        val layoutaManager = LinearLayoutManager(this)
        layoutaManager.orientation = LinearLayoutManager.VERTICAL
        list_parties.layoutManager = layoutaManager

        getData()
        askForPermission()

        var flot_new_party = findViewById<ImageView>(R.id.flot_new_party)
        var list_contacts = findViewById<RecyclerView>(R.id.list_contacts)
        flot_new_party.setOnClickListener {
            showAddPartyDialog()
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        list_contacts.layoutManager = layoutManager


    }

    fun showAddPartyDialog() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_add_new_party, null)
        val db = SqlDatabase(this)

        val btn_close = view.findViewById<ImageView>(R.id.btn_close)
        val txt_partyName = view.findViewById<EditText>(R.id.txt_partyName)
        val txt_mobileNumber = view.findViewById<EditText>(R.id.txt_mobileNumber)
        val txt_email = view.findViewById<EditText>(R.id.txt_email)

        val btn_customer = view.findViewById<CardView>(R.id.btn_customer)
        val btn_supplier = view.findViewById<CardView>(R.id.btn_supplier)

        val btn_add_book = view.findViewById<Button>(R.id.btn_add_book)

        var type = "";
        btn_customer.setOnClickListener {
            type = "Customer"
        }

        btn_supplier.setOnClickListener {
            type = "Supplier"
        }

        btn_add_book.setOnClickListener {
            if (txt_partyName.text.isNotEmpty()) {
                db.addParty(
                    IDGenrator.getId("PARTY"),
                    txt_partyName.text.toString(),
                    txt_mobileNumber.text.toString(),
                    txt_email.text.toString(),
                    type, getDateTime.getMilies(),
                )
                dialog.hide()
                getData()
            } else {
                txt_partyName.error = "Enter valid Party Name"
            }
        }

        btn_close.setOnClickListener {
            dialog.hide()
        }


        dialog.setContentView(view)
        dialog.show()
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, permissions, permissionCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionCode) {

            if (allPermissionGranted()) {
                //openCamera()
            } else {

            }

        }
    }

    private fun allPermissionGranted(): Boolean {
        for (item in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    item
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }

        return true
    }

}