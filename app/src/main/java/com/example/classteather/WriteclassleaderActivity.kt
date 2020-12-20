package com.example.classteather

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_writeclassleader.*

class WriteclassleaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writeclassleader)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        save.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1= ContentValues().apply{
                put("id",id1.text.toString())
                put("name",name1.text.toString())
                put("work",work1.text.toString())
            }
            db.insert("classLeader",null,values1)
            save.isEnabled=false
            Toast.makeText(this,"Save succeeded", Toast.LENGTH_SHORT).show()
        }
        back.setOnClickListener {
            val intent = Intent(this, ClassleaderActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
