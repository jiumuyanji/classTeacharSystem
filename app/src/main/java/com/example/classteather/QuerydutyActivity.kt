package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_queryduty.*

class QuerydutyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queryduty)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val idList=ArrayList<String>()
        val nameList=ArrayList<String>()
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
            val time2=time1.text
            if(time1.length()!=0 )
            {
                if(bool){
                    selection+=" and "
                }
                bool=true
                selection+="time = ?"
                selectionArgs.add(time2.toString())
            }
            val duty2=duty1.text
            if(duty1.length()!=0 )
            {
                if(bool){
                    selection+=" and "
                }
                selection+="duty = ?"
                selectionArgs.add(duty2.toString())
            }
            val cursor=db.query("duty",null,selection,selectionArgs.toTypedArray(),null,null,null)
            val studentList= ArrayList<String>()
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
            result.adapter=adapter
            Toast.makeText(this,"Search succeeded", Toast.LENGTH_SHORT).show()
        }
        result.setOnItemClickListener { _, _, position, _ ->
            val idToRead=idList[position]
            val nameToRead=nameList[position]
            val intent = Intent(this, ReaddutyActivity::class.java)
            intent.putExtra("extra_id",idToRead)
            intent.putExtra("extra_name",nameToRead)
            startActivity(intent)
        }
        back.setOnClickListener {
            val intent= Intent(this,DutylistActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
