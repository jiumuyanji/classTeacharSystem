package com.example.classteather

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_readstudent.*

class ReadstudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readstudent)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        var extraData=intent.getStringExtra("extra_id")
        val db = dbHelper.writableDatabase
        val selectionArgs = arrayOf(extraData.toString())
        val cursor =db.query("student",null,"id=?",selectionArgs,null,null,null,null)
        if(cursor.moveToFirst())
        {
            id1.text= SpannableStringBuilder(extraData.toString())
            name1.text=SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("name")))
            age1.text=SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("age")))
            sex1.text=SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("sex")))
            status1.text=SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("status")))
            comment1.text=SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("comment")))
        }
        visit.setOnClickListener {
            val intent=Intent(this,VisitlistActivity::class.java)
            intent.putExtra("extra_id",extraData.toString())
            intent.putExtra("extra_name",name1.text.toString())
            startActivity(intent)
            finish()
        }
        back.setOnClickListener {
            val intent= Intent(this,StudentActivity::class.java)
            startActivity(intent)
            finish()
        }
        update.setOnClickListener {
            val values1= ContentValues().apply{
                put("id",id1.text.toString())
                put("name",name1.text.toString())
                put("age",age1.text.toString())
                put("sex",sex1.text.toString())
                put("status",status1.text.toString())
                put("comment",comment1.text.toString())
            }
            db.update("student",values1,"id=?", arrayOf(id1.text.toString()))
            Toast.makeText(this,"Update succeeded", Toast.LENGTH_SHORT).show()
        }
        delete.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确认删除")
                setMessage("会删除该学生的信息")
                setCancelable(false)
                setPositiveButton("ok"){_, _->
                    db.delete("student","id=?", arrayOf(id1.text.toString()))
                }
                setNegativeButton("cancel",null)
                create()
                show()
            }
        }
    }
}
