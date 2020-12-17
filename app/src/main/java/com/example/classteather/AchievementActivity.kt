package com.example.classteather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_achievement.*

class AchievementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievement)
        addAchievement.setOnClickListener {
            val intent=Intent(this, AddactiecementActivity::class.java)
            startActivity(intent)
        }
        achievementList.setOnClickListener {
            val intent=Intent(this, AchievementlistActivity::class.java)
            startActivity(intent)
        }
        queryAchievement.setOnClickListener {
            val intent=Intent(this, QueryachievementActivity::class.java)
            startActivity(intent)
        }
        avgAchievement.setOnClickListener {
            val intent=Intent(this, AvgachievementActivity::class.java)
            startActivity(intent)
        }
        achievementNumber.setOnClickListener {
            val intent=Intent(this, AchievementnumberActivity::class.java)
            startActivity(intent)
        }
        back.setOnClickListener {
            finish()
        }
    }
}
