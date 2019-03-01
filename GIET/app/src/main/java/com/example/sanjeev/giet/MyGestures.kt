package com.example.sanjeev.giet

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast

class MyGestures:GestureDetector.OnGestureListener{
    var ctx:Context

    constructor(ctx: Context) {
        this.ctx = ctx
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
    return false
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        val dy1=  e1?.y
        val dy= e2!!.y
        if (dy1 != null) {
            if((dy1-dy)>50)
            {
                Toast.makeText(ctx,"You swiped up brother",Toast.LENGTH_LONG).show()
            }
        }

        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
   return false
    }

    override fun onLongPress(e: MotionEvent?) {
    }

}