package com.alexandre.bubblecheckbox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Setting up a spring animation to animate the view’s translationY property with the final
        // spring position at 0.
        val springAnim = SpringAnimation(hello_word, DynamicAnimation.TRANSLATION_Y, 0f)

        root_view.setOnClickListener({

        })

        root_view.setOnTouchListener( { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    //hello_word.x = motionEvent.x
                    //hello_word.y = motionEvent.y
                }
                MotionEvent.ACTION_UP -> {
                    view.performClick()

                }
            }
            return@setOnTouchListener true
        })
    }
}
