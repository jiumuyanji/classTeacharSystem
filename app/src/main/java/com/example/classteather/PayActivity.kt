package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_pay.*

class PayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("pay",null,null,null,null,null,null)
        val titleList= ArrayList<String>()
        val timeList=ArrayList<String>()
        val payList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val title =cursor.getString(cursor.getColumnIndex("title"))
                val time =cursor.getString(cursor.getColumnIndex("time"))
                titleList.add(title)
                timeList.add(time)
                payList.add("$title\n$time")
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,payList)
        list2.adapter=adapter

        list2.setOnItemClickListener { _, _, position, _ ->
            val titleToRead=titleList[position]
            val timeToRead=timeList[position]
            val intent= Intent(this,ReadpayActivity::class.java)
            intent.putExtra("extra_title",titleToRead)
            intent.putExtra("extra_time",timeToRead)
            startActivity(intent)
            finish()
        }
        add.setOnClickListener {
            val intent = Intent(this, WritepayActivity::class.java)
            startActivity(intent)
            finish()
        }
        back.setOnClickListener {
            finish()
        }
    }
}
