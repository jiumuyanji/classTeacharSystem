package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_dutylist.*

class DutylistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dutylist)
        val dbHelper = MyDatabaseHelper(this, "class.db", 1)
        val db = dbHelper.writableDatabase
        val cursor = db.query("student", null, null, null, null, null, null)
        val studentList = ArrayList<String>()
        val idList = ArrayList<String>()
        val nameList = ArrayList<String>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                studentList.add("$id $name")
                idList.add(id)
                nameList.add(name)
            } while (cursor.moveToNext())
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studentList)
        list1.adapter = adapter

        list1.setOnItemClickListener { _, _, position, _ ->
            val idToRead = idList[position]
            val nameToRead = nameList[position]
            val intent = Intent(this, ReaddutyActivity::class.java)
            intent.putExtra("extra_id", idToRead)
            intent.putExtra("extra_name", nameToRead)
            startActivity(intent)
        }

        query.setOnClickListener {
            val intent = Intent(this, QuerydutyActivity::class.java)
            startActivity(intent)
            finish()
        }

        back.setOnClickListener {
            finish()
        }
    }
}
