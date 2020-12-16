package com.example.classteather

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_readworksummary.*

class ReadworksummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readworksummary)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val extraTime=intent.getStringExtra("extra_time")
        val extraTitle=intent.getStringExtra("extra_title")
        val extraContent=intent.getStringExtra("extra_content")
        title1.text=extraTitle.toString()
        time1.text= SpannableStringBuilder(extraTime.toString())
        content1.text=SpannableStringBuilder(extraContent.toString())
        back.setOnClickListener {
            val intent= Intent(this,WorksummaryActivity::class.java)
            startActivity(intent)
            finish()
        }
        delete.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确认删除")
                setMessage("会删除该工作计划")
                setCancelable(false)
                setPositiveButton("ok"){_, _->
                    db.delete("workSummary","title = ? and time = ?", arrayOf(extraTitle.toString(),extraTime.toString()))
                }
                setNegativeButton("cancel",null)
                create()
                show()
            }
            Toast.makeText(this ,"Save succeeded", Toast.LENGTH_SHORT).show()
        }
        update.setOnClickListener {
            val values1 = ContentValues().apply {
                put("title", title1.text.toString())
                put("content", content1.text.toString())
                put("time", time1.text.toString())
            }
            db.update(
                "workSummary",
                values1,
                "title = ? and time = ?",
                arrayOf(extraTitle.toString(), extraTime.toString())
            )
            Toast.makeText(this, "Update succeeded", Toast.LENGTH_SHORT).show()
        }
    }
}
