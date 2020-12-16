package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_classwork.*

class ClassworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classwork)
        workPlan.setOnClickListener {
            val intent = Intent(this, PlanlistActivity::class.java)
            startActivity(intent)
        }
        worksummary.setOnClickListener {
            val intent = Intent(this, WorksummaryActivity::class.java)
            startActivity(intent)
        }
        classmeeting.setOnClickListener {
            val intent = Intent(this, ClassmeetinglistActivity::class.java)
            startActivity(intent)
        }
    }
}
