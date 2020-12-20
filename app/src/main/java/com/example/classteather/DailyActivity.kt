package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_daily.*

class DailyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        classLeader.setOnClickListener {
            val intent= Intent(this,ClassleaderActivity::class.java)
            startActivity(intent)
        }
        pay.setOnClickListener {
            val intent= Intent(this,PayActivity::class.java)
            startActivity(intent)
        }
        classActivity.setOnClickListener {
            val intent= Intent(this,ClassactivityActivity::class.java)
            startActivity(intent)
        }
        duty.setOnClickListener {
            val intent= Intent(this,DutylistActivity::class.java)
            startActivity(intent)
        }
        back.setOnClickListener {
            finish()
        }
    }
}
