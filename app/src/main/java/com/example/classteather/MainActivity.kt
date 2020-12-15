package com.example.classteather

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val prefs= getSharedPreferences("ZhucheActivity", Context.MODE_PRIVATE)
        begin.setOnClickListener {
            val account = prefs.getString("account","")
            val password = prefs.getString("password","")
            val account2=editText2.text.toString()
            val password2=editText3.text.toString()
            if (account == ""&& password=="") {
                Toast.makeText(this, "请输入用户名密码或注册账号", Toast.LENGTH_SHORT).show()
            }
            else if (account==account2&&password==password2){
                val intent = Intent(this, ChooseActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"密码错误，请检查", Toast.LENGTH_SHORT).show()
            }
        }
        register.setOnClickListener{
            val intent = Intent(this, ZhucheActivity::class.java)
            startActivity(intent)
            //finish()
        }
    }
}
