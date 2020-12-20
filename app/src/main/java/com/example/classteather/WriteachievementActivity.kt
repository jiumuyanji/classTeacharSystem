package com.example.classteather

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_writeachievement.*

class WriteachievementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writeachievement)
        Toast.makeText(this, "不能留空，用0代替", Toast.LENGTH_SHORT).show()
        val extraId = intent.getStringExtra("extra_id")
        val extraName = intent.getStringExtra("extra_name")
        val extraTextName = intent.getStringExtra("extra_textName")
        textView2.text = "$extraId $extraName"
        val dbHelper = MyDatabaseHelper(this, "class.db", 1)
        val db = dbHelper.writableDatabase
        val cursor = db.query(
            "achievement",
            null,
            "id=? and textName=?",
            arrayOf(extraId.toString(), extraTextName.toString()),
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            save.isEnabled = false
            Toast.makeText(this, "以保存过成绩，请检查考试名或学生名选择错误！", Toast.LENGTH_SHORT).show()
        }
        save.setOnClickListener {
            val total = YuWen1.text.toString().toDouble() + ShuXue1.text.toString().toDouble() +
                    WaiYu1.text.toString().toDouble() + WuLi1.text.toString().toDouble() +
                    HuaXue1.text.toString().toDouble() + ShengWu1.text.toString().toDouble() +
                    LiShi1.text.toString().toDouble() + ZhengZhi1.text.toString().toDouble() +
                    DiLi1.text.toString().toDouble()

            val values1 = ContentValues().apply {
                put("id", extraId)
                put("name", extraName)
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
            db.insert("achievement", null, values1)
            save.isEnabled = false
            Toast.makeText(this, "Save succeeded", Toast.LENGTH_SHORT).show()
        }
        back.setOnClickListener {
            finish()
        }
    }
}