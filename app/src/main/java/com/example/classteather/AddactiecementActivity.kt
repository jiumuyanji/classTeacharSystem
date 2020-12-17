package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_addactiecement.*

class AddactiecementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addactiecement)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("student",null,null,null,null,null,null)
        val studentList= ArrayList<String>()
        val nameList=ArrayList<String>()
        val idList=ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex("id"))
                val name =cursor.getString(cursor.getColumnIndex("name"))
                studentList.add("$id $name")
                idList.add(id)
                nameList.add(name)
            } while (cursor.moveToNext())
        }
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,studentList)
        list.adapter=adapter

        list.setOnItemClickListener { _, _, position, _ ->
            val textNameToRead=textName1.text.toString()
            if(textName1.length()==0)
            {
                Toast.makeText(this,"请输入考试名", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val idToRead=idList[position]
                val nameToRead=nameList[position]
                val intent = Intent(this, WriteachievementActivity::class.java)
                intent.putExtra("extra_id",idToRead)
                intent.putExtra("extra_name",nameToRead)
                intent.putExtra("extra_textName",textNameToRead)
                startActivity(intent)
            }
        }

        back.setOnClickListener {
            finish()
        }
    }
}
