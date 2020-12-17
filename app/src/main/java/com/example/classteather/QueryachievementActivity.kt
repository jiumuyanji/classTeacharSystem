package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_queryachievement.*

class QueryachievementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queryachievement)
        button.setOnClickListener {
            if(textName1.length()==0||id1.length()==0)
            {
                Toast.makeText(this,"请输入考试名或学号!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val dbHelper=MyDatabaseHelper(this,"class.db",1)
                val db = dbHelper.writableDatabase
                val cursor =db.query("achievement",null,"id=? and textName=?", arrayOf(id1.text.toString(),textName1.text.toString()),null,null,null,null)
                if(cursor.moveToFirst())
                {
                    YuWen1.text=cursor.getDouble(cursor.getColumnIndex("YuWen")).toString()
                    ShuXue1.text=cursor.getDouble(cursor.getColumnIndex("ShuXue")).toString()
                    WaiYu1.text=cursor.getDouble(cursor.getColumnIndex("WaiYu")).toString()
                    WuLi1.text= cursor.getDouble(cursor.getColumnIndex("WuLi")).toString()
                    HuaXue1.text=cursor.getDouble(cursor.getColumnIndex("HuaXue")).toString()
                    ShengWu1.text=cursor.getDouble(cursor.getColumnIndex("ShengWu")).toString()
                    LiShi1.text=cursor.getDouble(cursor.getColumnIndex("LiShi")).toString()
                    ZhengZhi1.text=cursor.getDouble(cursor.getColumnIndex("ZhengZhi")).toString()
                    DiLi1.text=cursor.getDouble(cursor.getColumnIndex("DiLi")).toString()
                    total1.text=cursor.getDouble(cursor.getColumnIndex("total")).toString()
                }
            }
        }
        back.setOnClickListener {
            finish()
        }
        update.setOnClickListener {
            val intent = Intent(this, ReadachievementActivity::class.java)
            intent.putExtra("extra_id",id1.text.toString())
            intent.putExtra("extra_textName",textName1.text.toString())
            startActivity(intent)
        }
    }
}
