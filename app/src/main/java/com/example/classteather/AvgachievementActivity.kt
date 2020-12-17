package com.example.classteather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_avgachievement.*

class AvgachievementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avgachievement)
        button.setOnClickListener {
            if(textName1.length()==0)
            {
                Toast.makeText(this,"请输入考试名!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val dbHelper=MyDatabaseHelper(this,"class.db",1)
                val db = dbHelper.writableDatabase
                val cursor =db.query("achievement",null,"textName=?", arrayOf(textName1.text.toString()),null,null,null,null)
                var yuWen=0.0
                var shuXue=0.0
                var waiYu=0.0
                var wuLi=0.0
                var huaXue=0.0
                var shengWu=0.0
                var liShi=0.0
                var zhengZhi=0.0
                var diLi=0.0
                var total=0.0
                if(cursor.moveToFirst())
                {
                    do {
                        yuWen+=cursor.getDouble(cursor.getColumnIndex("YuWen"))
                        shuXue+=cursor.getDouble(cursor.getColumnIndex("ShuXue"))
                        waiYu+=cursor.getDouble(cursor.getColumnIndex("WaiYu"))
                        wuLi+=cursor.getDouble(cursor.getColumnIndex("WuLi"))
                        huaXue+=cursor.getDouble(cursor.getColumnIndex("HuaXue"))
                        shengWu+=cursor.getDouble(cursor.getColumnIndex("ShengWu"))
                        liShi+=cursor.getDouble(cursor.getColumnIndex("LiShi"))
                        zhengZhi+=cursor.getDouble(cursor.getColumnIndex("ZhengZhi"))
                        diLi+=cursor.getDouble(cursor.getColumnIndex("DiLi"))
                        total+=cursor.getDouble(cursor.getColumnIndex("total"))
                    }while(cursor.moveToNext())
                    YuWen1.text=(yuWen/cursor.count).toString()
                    ShuXue1.text=(shuXue/cursor.count).toString()
                    WaiYu1.text=(waiYu/cursor.count).toString()
                    WuLi1.text=(wuLi/cursor.count).toString()
                    HuaXue1.text=(huaXue/cursor.count).toString()
                    ShengWu1.text=(shengWu/cursor.count).toString()
                    LiShi1.text=(liShi/cursor.count).toString()
                    ZhengZhi1.text=(zhengZhi/cursor.count).toString()
                    DiLi1.text=(diLi/cursor.count).toString()
                    total1.text=(total/cursor.count).toString()
                }
            }
        }
        back.setOnClickListener {
            finish()
        }
    }
}
