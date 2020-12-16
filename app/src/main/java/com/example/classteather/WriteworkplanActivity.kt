package com.example.classteather

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_writeworkplan.*

class WriteworkplanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writeworkplan)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        save.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1= ContentValues().apply{
                put("title",title1.text.toString())
                put("content",content1.text.toString())
                put("time",time1.text.toString())
            }
            db.insert("workPlan",null,values1)
            save.isEnabled=false
            Toast.makeText(this,"Save succeeded", Toast.LENGTH_SHORT).show()
        }
        back.setOnClickListener {
            val intent = Intent(this, PlanlistActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

