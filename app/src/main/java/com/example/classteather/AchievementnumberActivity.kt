package com.example.classteather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_achievementnumber.*

class AchievementnumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievementnumber)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        val db = dbHelper.writableDatabase
        query.setOnClickListener {
            if(textName1.length()==0)
            {
                Toast.makeText(this,"请输入考试名", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var selection="textName=?"
                val selectionArgs= arrayListOf<String>()
                selectionArgs.add(textName1.text.toString())
                if(little.length()!=0&&big.length()!=0)
                {
                    selection="textName=? and total>? and total<?"
                    selectionArgs.add(little.text.toString())
                    selectionArgs.add(big.text.toString())
                }
                else if(little.length()!=0)
                {
                    selection="textName=? and total>?"
                    selectionArgs.add(little.text.toString())
                }
                else if(big.length()!=0)
                {
                    selection="textName=? and total<?"
                    selectionArgs.add(big.text.toString())
                }
                val cursor=db.query("achievement",null,selection, selectionArgs.toTypedArray(),null,null,null)
                result.text = cursor.count.toString()
                }
            }
        back.setOnClickListener {
            finish()
        }
    }
}
