package com.example.free.playcanvasapp

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.AppCompatSeekBar
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent



/**
 * TODO: document your custom view class.
 */
class VerticalSeekBar : AppCompatSeekBar {
    var i = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onDraw(canvas: Canvas?) {
        //将SeekBar转转90度
        canvas?.rotate(-90f)
        //将旋转后的视图移动回来
        canvas?.translate(-width.toFloat(),200f)
        super.onDraw(canvas)
        canvas?.translate(0f, 0f)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) {
            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                //获取滑动的距离
                i = max - (max * event.y / width).toInt()
                //设置进度
                progress = i
                Log.i("Progress", progress.toString() + "")
                //每次拖动SeekBar都会调用
                onSizeChanged(width, height, 0, 0)
            }

            MotionEvent.ACTION_CANCEL -> {
            }
        }
        return true
    }
}
