package com.alexandre.bubblecheckbox.ui

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import com.alexandre.bubblecheckbox.R
import kotlinx.android.synthetic.main.bubble_radio_group.view.*
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator




class BubbleRadioGroup : CoordinatorLayout {

    private val DURATION = 1000.toLong()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.bubble_radio_group, this, true)

        bubble_radio_group.setOnCheckedChangeListener { buttonView, isChecked ->
            updateSelected()

        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if(changed)
            updateSelected()
    }

    private fun updateSelected() {

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            Log.i("tag", R.id.bubble_radio_group.toString())



            if(child.id == R.id.bubble_radio_group)
            {
                val radioGroup = child as RadioGroup
                for (u in 0 until radioGroup.childCount) {
                    val child2 = radioGroup.getChildAt(u)
                    val radioButton = child2 as RadioButton
                    Log.i("tag", String.format("2   %s %s %s %s", child2.x.toString(), child2.y.toString(), child2.width.toString(), child2.height.toString()))

                    Log.i("tag", String.format("2   %s %s", radioButton.text, radioButton.isChecked))

                    if(radioButton.isChecked)
                    {
                        animateButton(radioButton, DURATION)
                    }
                }
            }
        }
    }

    private fun animateButton(radioButton : RadioButton, duration : Long){
        animatePosition(radioButton, duration)
        animateWidth(radioButton, duration)
        animateHeight(radioButton, duration)
    }


    private fun animatePosition(radioButton : RadioButton, duration : Long){
        val animX = ObjectAnimator.ofFloat(bubble_background, "x", radioButton.x)
        val animY = ObjectAnimator.ofFloat(bubble_background, "y", radioButton.y)
        val animSetXY = AnimatorSet()
        animSetXY.duration = duration
        animSetXY.playTogether(animX, animY)
        animSetXY.start()
    }

    private fun animateWidth(radioButton : RadioButton, duration : Long)
    {
        val anim = ValueAnimator.ofInt(bubble_background.measuredWidth, radioButton.width)
        anim.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = bubble_background.layoutParams
            layoutParams.width = animatedValue
            bubble_background.layoutParams = layoutParams
        }
        anim.duration = duration
        anim.start()
    }

    private fun animateHeight(radioButton : RadioButton, duration : Long)
    {
        val animHeight = ValueAnimator.ofInt(bubble_background.measuredHeight, radioButton.height)
        animHeight.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = bubble_background.layoutParams
            layoutParams.height = animatedValue
            bubble_background.layoutParams = layoutParams
        }
        animHeight.duration = duration
        animHeight.start()
    }
}