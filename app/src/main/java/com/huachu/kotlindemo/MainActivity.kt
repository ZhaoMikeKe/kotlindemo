package com.huachu.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    var s: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        message.text = "Hello Kotlin!"
        postItem1()
        //main()
    }


    private fun postItem() {//同步
        GlobalScope.launch {
            val time = measureTimeMillis {
                val one = doSomethingUsefulOne()
                val two = doSomethingUsefulTwo()
                Log.d("zhooo", "The answer is ${one + two}")
            }
            Log.d("zhooo", "Completed in $time ms")
        }
    }

    private var job = Job()
    private fun postItem1() {//异步
        job = GlobalScope.launch {
            val time = measureTimeMillis {
                val one = async { doSomethingUsefulOne() }
                val two = async { doSomethingUsefulTwo() }
                Log.d("zhooo", "The answer is ${one.await() + two.await()}")
            }
            Log.d("zhooo", "Completed in $time ms")
        }
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // 假设我们在这里做了一些有用的事
        return 13
    }


    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // 假设我们在这里也做了一些有用的事
        return 29
    }

}
