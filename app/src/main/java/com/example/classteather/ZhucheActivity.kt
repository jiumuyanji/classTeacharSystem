package com.example.classteather

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_zhuche.*

class ZhucheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhuche)
        val prefs= getPreferences(Context.MODE_PRIVATE)
        btn_save.setOnClickListener {
            val account = et_phone.text.toString()
            val password = et_email.text.toString()
            val editor = prefs.edit()
            editor.putString("account", account)
            editor.putString("password", password)
            editor.apply()
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
