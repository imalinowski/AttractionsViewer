package com.example.attractionsviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {
    private lateinit var mScaleDetector: GestureDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mScaleDetector = GestureDetector(this,gestureDetector)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mScaleDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private val gestureDetector = object : GestureDetector.SimpleOnGestureListener(){
        private val delta = 100
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d("RASPBERRYD", "onFling: ${e1?.x?.let { e2?.x?.minus(it) }} ${e1?.y?.let { e2?.y?.minus(it) }} ")
            if(e1 != null && e2 != null &&
                e1.x - e2.x < delta && e1.y - e2.y > delta) {
                startActivity(Intent(this@MainActivity,PlayerActivity::class.java))
            }
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }
}