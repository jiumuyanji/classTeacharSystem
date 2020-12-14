package com.example.classteather

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_writevisit.*

class WritevisitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writevisit)
        val extraId=intent.getStringExtra("extra_id")
        val extraName=intent.getStringExtra("extra_name")
        id1.text=extraId.toString()
        name1.text=extraName.toString()
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        save.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1= ContentValues().apply{
                put("id",id1.text.toString())
                put("name",name1.text.toString())
                put("content",content1.text.toString())
                put("time",time1.text.toString())
            }
            db.insert("visit",null,values1)
            Toast.makeText(this,"Save succeeded", Toast.LENGTH_SHORT).show()
        }
        back.setOnClickListener {
            val intent= Intent(this,VisitlistActivity::class.java)
            intent.putExtra("extra_id",extraId.toString())
            intent.putExtra("extra_name",extraName.toString())
            startActivity(intent)
            finish()
        }
    }
}
