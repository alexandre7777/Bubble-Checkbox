package com.alexandre.bubbleradiogroup.ui

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RadioButton
import com.alexandre.bubbleradiogroup.R
import kotlinx.android.synthetic.main.bubble_radio_group.view.*
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
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
            bubble_radio_group.orientation = a.getInt(R.styleable.BubbleCheckBox_orientation, DEFAULT_ORIENTATION)
            bubble_background.background = a.getDrawable(R.styleable.BubbleCheckBox_background)
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

    override fun onFinishInflate() {
        super.onFinishInflate()

        updateViewHierarchy()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if(changed)
            updateSelected()
    }

    /**
     * move RadioButton created in the root view to the bubble radio group subview
     */
    private fun updateViewHierarchy(){
        val viewsToChange : MutableList<View> = mutableListOf<View>()

        for (i in 0 until childCount) {
            val child = getChildAt(i)

            if (child!= null && !(child.id == R.id.bubble_radio_group || child.id == R.id.bubble_background)) {
                viewsToChange.add(child)
            }
        }

        for(view in viewsToChange)
        {
            (rootView as CoordinatorLayout).removeView(view)
            bubble_radio_group.addView(view)
        }
    }

    /**
     * for the checked radio button call animation methods
     */
    private fun updateSelected() {
        for (u in 0 until bubble_radio_group.childCount) {
            val child2 = bubble_radio_group.getChildAt(u)
            val radioButton = child2 as RadioButton
            if(radioButton.isChecked) {
                animateButton(radioButton, duration)
            }
        }
    }

    /**
     * call all animation methods
     */
    private fun animateButton(radioButton : RadioButton, duration : Long){
        animatePosition(radioButton, duration)
        animateWidth(radioButton, duration)
        animateHeight(radioButton, duration)
    }

    /**
     * animate the position of the background
     */
    private fun animatePosition(radioButton : RadioButton, duration : Long){
        val animX = ObjectAnimator.ofFloat(bubble_background, "x", radioButton.x)
        val animY = ObjectAnimator.ofFloat(bubble_background, "y", radioButton.y)
        val animSetXY = AnimatorSet()
        animSetXY.duration = duration
        animSetXY.playTogether(animX, animY)
        animSetXY.start()
    }

    /**
     * animate the width of the background
     */
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

    /**
     * animate the height of the background
     */
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