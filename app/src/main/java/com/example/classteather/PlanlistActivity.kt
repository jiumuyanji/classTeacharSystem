package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_planlist.*

class PlanlistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planlist)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("workPlan",null,null,null,null,null,null)
        val titleList= ArrayList<String>()
        val titleList1= ArrayList<String>()
        val contentList=ArrayList<String>()
        val timeList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val time =cursor.getString(cursor.getColumnIndex("time"))
                val content=cursor.getString(cursor.getColumnIndex("content"))
                titleList1.add("$title\n$time")
                titleList.add(title)
                timeList.add(time)
                contentList.add(content)
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titleList1)
        list2.adapter=adapter

        list2.setOnItemClickListener { _, _, position, _ ->
            val timeToRead=timeList[position]
            val contentToRead=contentList[position]
            val titleToRead=titleList[position]
            val intent= Intent(this,ReadworkplanActivity::class.java)
            intent.putExtra("extra_title",titleToRead)
            intent.putExtra("extra_time",timeToRead)
            intent.putExtra("extra_content",contentToRead)
            startActivity(intent)
            finish()
        }

        add.setOnClickListener {
            val intent = Intent(this, WriteworkplanActivity::class.java)
            startActivity(intent)
            finish()
        }

        back.setOnClickListener {
            finish()
        }
    }
    override fun onRestart() {
        super.onRestart()
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("workPlan",null,null,null,null,null,null)
        val titleList= ArrayList<String>()
        val titleList1= ArrayList<String>()
        val contentList=ArrayList<String>()
        val timeList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val time =cursor.getString(cursor.getColumnIndex("time"))
                val content=cursor.getString(cursor.getColumnIndex("content"))
                titleList1.add("$title\n$time")
                titleList.add(title)
                timeList.add(time)
                contentList.add(content)
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titleList1)
        list2.adapter=adapter
    }
}
