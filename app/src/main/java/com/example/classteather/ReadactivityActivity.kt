package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_readactivity.*

class ReadactivityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readactivity)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val extraTime=intent.getStringExtra("extra_time")
        val extraTheme=intent.getStringExtra("extra_theme")
        val extraContent=intent.getStringExtra("extra_content")
        theme1.text=extraTheme.toString()
        time1.text= extraTime.toString()
        content1.text=extraContent.toString()
        back.setOnClickListener {
            val intent= Intent(this,ClassactivityActivity::class.java)
            startActivity(intent)
            finish()
        }
        delete.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确认删除")
                setMessage("会删除该活动的信息")
                setCancelable(false)
                setPositiveButton("ok"){_, _->
                    db.delete("classActivity","theme = ? and time = ?", arrayOf(extraTheme.toString(),extraTime.toString()))
                }
                setNegativeButton("cancel",null)
                create()
                show()
            }
        }
    }
}
