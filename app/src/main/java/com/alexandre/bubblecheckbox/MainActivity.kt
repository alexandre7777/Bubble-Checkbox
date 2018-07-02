package com.alexandre.bubblecheckbox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        first_radio_group.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("example", "buttonView : " + buttonView.id + "isChecked : " + isChecked)
        }

        second_radio_group.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("example", "buttonView : " + buttonView.id + "isChecked : " + isChecked)
        }
    }
}
