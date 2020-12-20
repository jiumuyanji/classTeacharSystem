package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_readtalk.*

class ReadtalkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readtalk)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val extraTime=intent.getStringExtra(("extra_time"))
        val extraId=intent.getStringExtra("extra_id")
        val extraName=intent.getStringExtra("extra_name")
        val extraContent=intent.getStringExtra("extra_content")
        id1.text=extraId.toString()
        name1.text=extraName.toString()
        time1.text=extraTime.toString()
        content1.text=extraContent.toString()
        back.setOnClickListener {
            val intent= Intent(this,TalklistActivity::class.java)
            intent.putExtra("extra_id",extraId.toString())
            intent.putExtra("extra_name",extraName.toString())
            startActivity(intent)
            finish()
        }
        delete.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确认删除")
                setMessage("会删除该学生的谈话信息")
                setCancelable(false)
                setPositiveButton("ok"){_, _->
                    db.delete("talk","name = ? and time = ?", arrayOf(extraName.toString(),extraTime.toString()))
                }
                setNegativeButton("cancel",null)
                create()
                show()
            }
        }
    }
}
