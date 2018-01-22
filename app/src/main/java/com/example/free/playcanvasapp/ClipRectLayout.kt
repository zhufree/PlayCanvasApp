package com.example.free.playcanvasapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.widget.AppCompatSeekBar
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.*

/**
 * Created by zhufree on 2018/1/22.
 *
 */
class ClipRectLayout : LinearLayout {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null
    var seekBar: AppCompatSeekBar? = null
    var leftBorder: Float = 0f
    var topBorder: Float = 70f
    var rightBorder: Float = 0f
    var bottomBorder: Float = 0f

    val TAG = "TAG"

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.music)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        seekBar = findViewById(R.id.seek_bar)
//        durationValueTv = findViewById(R.id.durationValueTv) as TextView
//        durationValueTv.setText(context.getString(R.string.ms_with_value, duration))
        seekBar?.max = 100
        seekBar?.progress = 20
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                leftBorder = progress * 0.01f * width
                Log.i(TAG, "" + leftBorder)
                invalidate()
//                durationValueTv.setText(context.getString(R.string.ms_with_value, duration))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

    }


    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        rightBorder = canvas!!.width.toFloat()
        bottomBorder = canvas.height.toFloat()
        canvas.save()
//        原来的方形范围是 left, top, left + bitmap.width, top + bitmap.height
//        left: 左边到容器左边的距离
//        top: 上边到容器顶部的距离
//        right: 右边到容器左边的距离，所以是left + width
//        bottom: 同理
        Log.i(TAG, " "+ leftBorder+ topBorder+rightBorder+ bottomBorder)
        canvas.clipRect(leftBorder, topBorder, rightBorder, bottomBorder)
        canvas.drawBitmap(bitmap,  width.div(2)-bitmap?.width!!.div(2f), topBorder, paint)
        canvas.restore()
    }
}