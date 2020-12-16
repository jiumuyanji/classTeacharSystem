package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_readclassmeeting.*

class ReadclassmeetingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readclassmeeting)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val extraTime=intent.getStringExtra("extra_time")
        val extraTheme=intent.getStringExtra("extra_theme")
        val extraContent=intent.getStringExtra("extra_content")
        theme1.text=extraTheme.toString()
        time1.text= SpannableStringBuilder(extraTime.toString())
        content1.text=SpannableStringBuilder(extraContent.toString())
        back.setOnClickListener {
            val intent= Intent(this,ClassmeetinglistActivity::class.java)
            startActivity(intent)
            finish()
        }
        delete.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确认删除")
                setMessage("会删除该主题班会信息")
                setCancelable(false)
                setPositiveButton("ok"){_, _->
                    db.delete("classMeeting","theme = ? and time = ?", arrayOf(extraTheme.toString(),extraTime.toString()))
                }
                setNegativeButton("cancel",null)
                create()
                show()
            }
            Toast.makeText(this ,"Save succeeded", Toast.LENGTH_SHORT).show()
        }
    }
}
