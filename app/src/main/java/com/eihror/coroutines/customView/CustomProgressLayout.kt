package com.eihror.coroutines.customView

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.support.v4.content.ContextCompat
import android.text.Layout
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class CustomProgressLayout : LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private lateinit var txtTitle: TextView

    private var lp: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
    )

    init {

        //Set width and height
        layoutParams = lp
        //Set Layout background color
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.black))
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

    fun Context.getAct(): Activity? {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    fun show(show: Boolean) {
        var rootView = context.getAct()?.window?.decorView?.findViewById<ViewGroup>(android.R.id.content)!!

        if (show) {
            rootView.addView(this@CustomProgressLayout, childCount)
        } else {
            rootView.removeView(this@CustomProgressLayout)
        }
    }
}
