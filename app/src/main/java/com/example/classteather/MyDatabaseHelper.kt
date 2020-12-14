package com.example.classteather

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper (val context: Context, name:String, version:Int):
    SQLiteOpenHelper(context,name,null,version)
{

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table student(id text,name text,age text,sex text,status text,comment text)")
        db.execSQL("create table visit(id text,name text,content text,time text)")
        Toast.makeText(context,"Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}