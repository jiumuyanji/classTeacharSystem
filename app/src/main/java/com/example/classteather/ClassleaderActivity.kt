package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_classleader.*

class ClassleaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classleader)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("classLeader",null,null,null,null,null,null)
        val leaderList= ArrayList<String>()
        val idList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val name =cursor.getString(cursor.getColumnIndex("name"))
                val id =cursor.getString(cursor.getColumnIndex("id"))
                val work=cursor.getString(cursor.getColumnIndex("work"))
                leaderList.add("$work:$name\n$id")
                idList.add(id)
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,leaderList)
        list2.adapter=adapter

        list2.setOnItemClickListener { _, _, position, _ ->
            val idToRead=idList[position]
            AlertDialog.Builder(this).apply {
                setTitle("确认删除")
                setMessage("会删除该班干部信息")
                setCancelable(false)
                setPositiveButton("ok"){_, _->
                    db.delete("classLeader","id=?", arrayOf(idToRead))
                    onRestart()
                }
                setNegativeButton("cancel",null)
                create()
                show()
            }
        }
        add.setOnClickListener {
            val intent = Intent(this,WriteclassleaderActivity::class.java)
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
        val cursor=db.query("classLeader",null,null,null,null,null,null)
        val leaderList= ArrayList<String>()
        val idList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val name =cursor.getString(cursor.getColumnIndex("name"))
                val id =cursor.getString(cursor.getColumnIndex("id"))
                val work=cursor.getString(cursor.getColumnIndex("work"))
                leaderList.add("$work:$name\n$id")
                idList.add(id)
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,leaderList)
        list2.adapter=adapter
    }
}
