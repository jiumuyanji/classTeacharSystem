package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_querystudent.*

class QueryStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_querystudent)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val idList=ArrayList<String>()
        query.setOnClickListener {
            val id2=id1.text
            var selection=""
            val selectionArgs= arrayListOf<String>()
            var bool=false
            if(id1.length()!=0 )
            {
                bool=true
                selection+="id=?"
                selectionArgs.add(id2.toString())
            }
            val name2=name1.text
            if(name1.length()!=0)
            {
                if(bool){
                    selection+=" and "
                }
                bool=true
                selection+="name = ?"
                selectionArgs.add(name2.toString())
            }
            val age2=age1.text
            if(age1.length()!=0 )
            {
                if(bool){
                    selection+=" and "
                }
                bool=true
                selection+="age = ?"
                selectionArgs.add(age2.toString())
            }
            val sex2=sex1.text
            if(sex1.length()!=0 )
            {
                if(bool){
                    selection+=" and "
                }
                bool=true
                selection+="sex = ?"
                selectionArgs.add(sex2.toString())
            }
            val status2=status1.text
            if(status1.length()!=0)
            {
                if(bool){
                    selection+=" and "
                }
                selection+="status = ?"
                selectionArgs.add(status2.toString())
            }
            val cursor=db.query("student",null,selection,selectionArgs.toTypedArray(),null,null,null)
            val studentList= ArrayList<String>()
            if(cursor.moveToFirst()) {
                do {
                    val id = cursor.getString(cursor.getColumnIndex("id"))
                    val name =cursor.getString(cursor.getColumnIndex("name"))
                    studentList.add("$id $name")
                    idList.add(id)
                } while (cursor.moveToNext())
            }
            val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,studentList)
            result.adapter=adapter
            Toast.makeText(this,"Search succeeded", Toast.LENGTH_SHORT).show()
        }
        result.setOnItemClickListener { _, _, position, _ ->
            val idToRead=idList[position]
            val intent = Intent(this, ReadstudentActivity::class.java)
            intent.putExtra("extra_id",idToRead)
            startActivity(intent)
            finish()
        }
        back.setOnClickListener {
            val intent= Intent(this,StudentActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
