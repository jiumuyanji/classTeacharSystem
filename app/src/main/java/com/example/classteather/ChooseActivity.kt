package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        button1.setOnClickListener {
            val intent = Intent(this, StudentActivity::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent=Intent(this,ClassworkActivity::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent=Intent(this,AchievementActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent=Intent(this,DailyActivity::class.java)
            startActivity(intent)
        }
        button5.setOnClickListener {
            val intent=Intent(this,ZhucheActivity::class.java)
            startActivity(intent)
        }
    }
}
