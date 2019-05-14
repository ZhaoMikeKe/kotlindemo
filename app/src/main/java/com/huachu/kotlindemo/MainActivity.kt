package com.huachu.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        message.text = "Hello Kotlin!"
        toast("Hello")
        toast("Hello", Toast.LENGTH_LONG)
    }

    fun add(x: Int, y: Int): Int {
        return x + y
    }

    fun add1(x: Int, y: Int): Int = x + y

    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }



}
