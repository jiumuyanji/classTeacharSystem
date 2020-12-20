package com.example.classteather

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_readduty.*

class ReaddutyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readduty)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db=dbHelper.writableDatabase
        val extraId=intent.getStringExtra("extra_id")
        val extraName=intent.getStringExtra("extra_name")
        id1.text=extraId.toString()
        name1.text= extraName.toString()
        val cursor =db.query("duty",null,"id=?", arrayOf(extraId),null,null,null,null)
        if(cursor.moveToFirst())
        {
            duty1.text= SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("duty")))
            time1.text=SpannableStringBuilder(cursor.getString(cursor.getColumnIndex("time")))
            save.isEnabled=false
        }
        else
        {
            update.isEnabled=false
        }
        back.setOnClickListener {
            finish()
        }
        save.setOnClickListener {
            val values1= ContentValues().apply{
                put("id",id1.text.toString())
                put("name",name1.text.toString())
                put("duty",duty1.text.toString())
                put("time",time1.text.toString())
            }
            db.insert("duty", null, values1)
            save.isEnabled = false
            Toast.makeText(this, "Save succeeded", Toast.LENGTH_SHORT).show()
        }
        update.setOnClickListener {
            val values1= ContentValues().apply{
                put("id",id1.text.toString())
                put("name",name1.text.toString())
                put("duty",duty1.text.toString())
                put("time",time1.text.toString())
            }
            db.update("duty",values1,"id=?", arrayOf(extraId))
            Toast.makeText(this,"Update succeeded", Toast.LENGTH_SHORT).show()
        }
    }
}
