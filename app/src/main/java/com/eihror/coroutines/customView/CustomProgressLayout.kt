package com.eihror.coroutines.customView

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView

class CustomProgressLayout : LinearLayout {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private lateinit var txtTitle: TextView

    private fun init(context: Context) {

        var lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        )

        //Set width and height
        layoutParams = lp
        //Set Layout background color
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        orientation = LinearLayout.VERTICAL
        weightSum = 6f

        /**
         * Set Title on Progress Layout
         */
        txtTitle = TextView(context)
        txtTitle.text = "Works"
        txtTitle.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        txtTitle.textSize = 30F

        this.addView(txtTitle)

    }

    fun show(show: Boolean) {
        if (show) {
            addView(this@CustomProgressLayout, childCount)
        } else {
            removeView(this@CustomProgressLayout)
        }
    }
}
