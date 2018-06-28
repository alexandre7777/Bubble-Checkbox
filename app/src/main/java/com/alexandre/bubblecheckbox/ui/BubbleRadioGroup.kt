package com.alexandre.bubblecheckbox.ui

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RadioButton
import com.alexandre.bubblecheckbox.R
import kotlinx.android.synthetic.main.bubble_radio_group.view.*
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.widget.LinearLayout

class BubbleRadioGroup : CoordinatorLayout {

    private val DEFAULT_DURATION = 100
    private val DEFAULT_ORIENTATION = LinearLayout.HORIZONTAL
    private var duration = DEFAULT_DURATION.toLong()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.BubbleCheckBox,
                0, 0)

        try {
            duration = a.getInt(R.styleable.BubbleCheckBox_duration, DEFAULT_DURATION).toLong()
            val orientation = a.getInt(R.styleable.BubbleCheckBox_orientation, DEFAULT_ORIENTATION)
            bubble_radio_group.orientation = orientation

        } finally {
            a.recycle();
        }
    }

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
        for (u in 0 until bubble_radio_group.childCount) {
            val child2 = bubble_radio_group.getChildAt(u)
            val radioButton = child2 as RadioButton
            if(radioButton.isChecked) {
                animateButton(radioButton, duration)
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