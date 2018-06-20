package com.alexandre.bubblecheckbox.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.RadioButton

class BubbleRadioButton : RadioButton {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    /*override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)

        Log.d("TAG", "position x " + x);
        Log.d("TAG", "position y " + y);

        if(isChecked)
        {
            Log.d("TAG", "check -> uncheck " + text);
            isChecked = false
        }
        else
        {
            Log.d("TAG", "uncheck -> check " + text);
            isChecked = true
        }
        return false
    }*/
}