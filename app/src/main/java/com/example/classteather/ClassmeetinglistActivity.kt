package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_classmeetinglist.*

class ClassmeetinglistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classmeetinglist)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("classMeeting",null,null,null,null,null,null)
        val themeList= ArrayList<String>()
        val themeList1= ArrayList<String>()
        val contentList=ArrayList<String>()
        val timeList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val theme = cursor.getString(cursor.getColumnIndex("theme"))
                val time =cursor.getString(cursor.getColumnIndex("time"))
                val content=cursor.getString(cursor.getColumnIndex("content"))
                themeList1.add("$theme\n$time")
                themeList.add(theme)
                timeList.add(time)
                contentList.add(content)
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,themeList1)
        list2.adapter=adapter

        list2.setOnItemClickListener { _, _, position, _ ->
            val timeToRead=timeList[position]
            val contentToRead=contentList[position]
            val themeToRead=themeList[position]
            val intent= Intent(this,ReadclassmeetingActivity::class.java)
            intent.putExtra("extra_theme",themeToRead)
            intent.putExtra("extra_time",timeToRead)
            intent.putExtra("extra_content",contentToRead)
            startActivity(intent)
            finish()
        }

        add.setOnClickListener {
            val intent = Intent(this, WriteclassmeetingActivity::class.java)
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
        val cursor=db.query("classMeeting",null,null,null,null,null,null)
        val themeList= ArrayList<String>()
        val themeList1= ArrayList<String>()
        val contentList=ArrayList<String>()
        val timeList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val theme = cursor.getString(cursor.getColumnIndex("theme"))
                val time =cursor.getString(cursor.getColumnIndex("time"))
                val content=cursor.getString(cursor.getColumnIndex("content"))
                themeList1.add("$theme\n$time")
                themeList.add(theme)
                timeList.add(time)
                contentList.add(content)
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,themeList1)
        list2.adapter=adapter
    }
}
