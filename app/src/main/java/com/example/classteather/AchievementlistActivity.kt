package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_achievementlist.*

class AchievementlistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievementlist)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val studentList= ArrayList<String>()
        val idList=ArrayList<String>()
        go.setOnClickListener {
            if(textName1.length()==0)
            {
                Toast.makeText(this,"请输入考试名", Toast.LENGTH_SHORT).show()
            }
            else{
                var adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,studentList)
                adapter.clear()
                val cursor=db.query("achievement",null,"textName=?",
                    arrayOf(textName1.text.toString()),null,null,"total desc")
                var count=0
                if(cursor.moveToFirst()) {
                    do {
                        count++
                        val id = cursor.getString(cursor.getColumnIndex("id"))
                        val name =cursor.getString(cursor.getColumnIndex("name"))
                        val total =cursor.getDouble(cursor.getColumnIndex("total"))
                        studentList.add("$id   $name\n 排名：$count   总分：$total")
                        idList.add(id)
                    } while (cursor.moveToNext())
                }
                adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,studentList)
                list.adapter=adapter
                Toast.makeText(this,"Search succeeded", Toast.LENGTH_SHORT).show()
            }
        }
        list.setOnItemClickListener { _, _, position, _ ->
            val idToRead=idList[position]
            val intent = Intent(this, ReadachievementActivity::class.java)
            intent.putExtra("extra_id",idToRead)
            intent.putExtra("extra_textName",textName1.text.toString())
            startActivity(intent)
        }
        back.setOnClickListener {
            finish()
        }
    }
}
