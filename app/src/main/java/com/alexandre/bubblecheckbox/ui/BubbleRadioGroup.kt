package com.alexandre.bubblecheckbox.ui

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import com.alexandre.bubblecheckbox.R
import kotlinx.android.synthetic.main.bubble_radio_group.view.*



class BubbleRadioGroup : CoordinatorLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.bubble_radio_group, this, true)

        bubble_radio_group.setOnCheckedChangeListener { buttonView, isChecked ->
            updateSelected()

        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //TODO get size of radio button selected
        /*val layoutParams = bubble_background.getLayoutParams() as CoordinatorLayout.LayoutParams
        layoutParams.width = LayoutParams.MATCH_PARENT
        layoutParams.height = LayoutParams.MATCH_PARENT

        bubble_background.layoutParams = layoutParams*/


    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        updateSelected()
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        //bubble_background.x = ev.x
        //bubble_background.y = ev.y

        //updateSelected()
        return super.onTouchEvent(ev)
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
                        bubble_background.x = radioButton.x
                        bubble_background.y = radioButton.y

                        val params = bubble_background.getLayoutParams()
                        params.width = radioButton.width
                        params.height = radioButton.height

                        bubble_background.layoutParams = params
                    }
                }
            }
        }
    }
}