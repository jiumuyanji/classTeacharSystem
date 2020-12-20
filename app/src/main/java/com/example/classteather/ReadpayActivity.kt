package com.example.classteather

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_readpay.*

class ReadpayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readpay)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val extraTitle=intent.getStringExtra("extra_title")
        val extraTime=intent.getStringExtra("extra_time")
        val db = dbHelper.writableDatabase
        val selectionArgs = arrayOf(extraTitle.toString(),extraTime.toString())
        val cursor =db.query("pay",null,"title=? and time=?",selectionArgs,null,null,null,null)
        if(cursor.moveToFirst())
        {
            title1.text=extraTitle.toString()
            want1.text=SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("want")))
            last1.text= SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("last")))
            num1.text=SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("num")))
            time1.text=extraTime.toString()
            content1.text=SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("content")))
        }

        back.setOnClickListener {
            val intent= Intent(this,PayActivity::class.java)
            startActivity(intent)
            finish()
        }
        update.setOnClickListener {
            val values1= ContentValues().apply{
                put("title",title1.text.toString())
                put("want",want1.text.toString())
                put("last",last1.text.toString())
                put("num",num1.text.toString())
                put("time",time1.text.toString())
                put("content",content1.text.toString())
            }
            db.update("pay",values1,"title=? and time=?", arrayOf(extraTitle.toString(),extraTime.toString()))
            Toast.makeText(this,"Update succeeded", Toast.LENGTH_SHORT).show()
        }
        delete.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确认删除")
                setMessage("会删除该缴费信息")
                setCancelable(false)
                setPositiveButton("ok"){_, _->
                    db.delete("pay","title=? and time=?", arrayOf(extraTitle.toString(),extraTime.toString()))
                }
                setNegativeButton("cancel",null)
                create()
                show()
            }
        }
    }
}
