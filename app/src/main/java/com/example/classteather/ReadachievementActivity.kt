package com.example.classteather

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_readachievement.*


class ReadachievementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readachievement)
        val extraId =intent.getStringExtra("extra_id")
        val extraTextName=intent.getStringExtra("extra_textName")
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db = dbHelper.writableDatabase
        val cursor =db.query("achievement",null,"id=? and textName=?", arrayOf(extraId.toString(),extraTextName.toString()),null,null,null,null)
        var name=""
        if(cursor.moveToFirst())
        {
            name=cursor.getString(cursor.getColumnIndex("name"))
            textView2.text= SpannableStringBuilder("$extraId $name")
            YuWen1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("YuWen")).toString())
            ShuXue1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("ShuXue")).toString())
            WaiYu1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("WaiYu")).toString())
            WuLi1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("WuLi")).toString())
            HuaXue1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("HuaXue")).toString())
            ShengWu1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("ShengWu")).toString())
            LiShi1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("LiShi")).toString())
            ZhengZhi1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("ZhengZhi")).toString())
            DiLi1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("DiLi")).toString())
            total1.text=SpannableStringBuilder(cursor.getDouble(cursor.getColumnIndex("total")).toString())
        }
        back.setOnClickListener {
            finish()
        }
        update.setOnClickListener {
            val total = YuWen1.text.toString().toDouble() + ShuXue1.text.toString().toDouble() +
                    WaiYu1.text.toString().toDouble() + WuLi1.text.toString().toDouble() +
                    HuaXue1.text.toString().toDouble() + ShengWu1.text.toString().toDouble() +
                    LiShi1.text.toString().toDouble() + ZhengZhi1.text.toString().toDouble() +
                    DiLi1.text.toString().toDouble()
            val values1= ContentValues().apply{
                put("id", extraId)
                put("name", name)
                put("textName", extraTextName)
                put("YuWen", YuWen1.text.toString().toDouble())
                put("ShuXue", ShuXue1.text.toString().toDouble())
                put("WaiYu", WaiYu1.text.toString().toDouble())
                put("WuLi", WuLi1.text.toString().toDouble())
                put("HuaXue", HuaXue1.text.toString().toDouble())
                put("ShengWu", ShengWu1.text.toString().toDouble())
                put("LiShi", LiShi1.text.toString().toDouble())
                put("ZhengZhi", ZhengZhi1.text.toString().toDouble())
                put("DiLi", DiLi1.text.toString().toDouble())
                put("total", total)
            }
            db.update("achievement",values1,"id = ? and textName = ?", arrayOf(extraId.toString(),extraTextName.toString()))
            Toast.makeText(this,"Update succeeded", Toast.LENGTH_SHORT).show()
        }
    }
}
