package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_talklist.*

class TalklistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_talklist)
        val extraId=intent.getStringExtra("extra_id")
        val extraName=intent.getStringExtra("extra_name")
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("talk",null,"id=?",arrayOf(extraId.toString()),null,null,null)
        val visitList= ArrayList<String>()
        val nameList=ArrayList<String>()
        val timeList=ArrayList<String>()
        val contentList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val name =cursor.getString(cursor.getColumnIndex("name"))
                val time =cursor.getString(cursor.getColumnIndex("time"))
                val content=cursor.getString(cursor.getColumnIndex("content"))
                visitList.add("$name $time")
                nameList.add(name)
                timeList.add(time)
                contentList.add(content)
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,visitList)
        list2.adapter=adapter

        list2.setOnItemClickListener { _, _, position, _ ->
            val timeToRead=timeList[position]
            val contentToRead=contentList[position]
            val intent= Intent(this,ReadtalkActivity::class.java)
            intent.putExtra("extra_id",extraId.toString())
            intent.putExtra("extra_name",extraName.toString())
            intent.putExtra("extra_time",timeToRead)
            intent.putExtra("extra_content",contentToRead)
            startActivity(intent)
            finish()
        }

        add.setOnClickListener {
            val intent = Intent(this, WritetalkActivity::class.java)
            intent.putExtra("extra_id",extraId.toString())
            intent.putExtra("extra_name",extraName.toString())
            startActivity(intent)
            finish()
        }
        back.setOnClickListener {
            val intent = Intent(this, ReadstudentActivity::class.java)
            intent.putExtra("extra_id",extraId)
            startActivity(intent)
            finish()
        }
    }
    override fun onRestart() {
        super.onRestart()
        var extraData=intent.getStringExtra("extra_id")
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("talk",null,"id=?", arrayOf(extraData),null,null,null)
        val visitList= ArrayList<String>()
        val nList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val n=cursor.getString(cursor.getColumnIndex("num"))
                val name =cursor.getString(cursor.getColumnIndex("name"))
                val time =cursor.getString(cursor.getColumnIndex("time"))
                visitList.add("$name $time")
                nList.add(n)
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,visitList)
        list2.adapter=adapter
    }
}
