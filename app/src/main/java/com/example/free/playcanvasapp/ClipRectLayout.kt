package com.example.free.playcanvasapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.widget.AppCompatSeekBar
import android.util.AttributeSet
import android.widget.*

/**
 * Created by zhufree on 2018/1/22.
 *
 */
class ClipRectLayout : LinearLayout {
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var horizontalSeekBar: AppCompatSeekBar? = null
    private var verticalSeekBar: VerticalSeekBar? = null
    var bitmap: Bitmap? = null
    var leftBorder: Float = 0f
    var topBorder: Float = 0f
    private var rightBorder: Float = 0f
    private var bottomBorder: Float = 0f


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.music)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        horizontalSeekBar = findViewById(R.id.horizontal_seek_bar)
        verticalSeekBar = findViewById(R.id.vertical_seek_bar)
        horizontalSeekBar?.progress = 20
        verticalSeekBar?.progress = 100
        horizontalSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                leftBorder = progress * 0.01f * bitmap!!.width
                invalidate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
        verticalSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                topBorder = (100-progress) * 0.01f * bitmap!!.height
                invalidate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

    }


    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

//        绘图位置不变，滑动时修改clip边距
        rightBorder = canvas!!.width.toFloat()
        bottomBorder = canvas.height.toFloat()
        val leftStart = width.div(2) - bitmap?.width!!.div(2f)
        val topStart = height.div(2) - bitmap?.height!!.div(2f)
        canvas.save()
//        原来的方形范围是 left, top, left + bitmap.width, top + bitmap.height
//        left: 左边到容器左边的距离
//        top: 上边到容器顶部的距离
//        right: 右边到容器左边的距离，所以是left + width
//        bottom: 同理
//        Log.i(TAG, " "+ leftBorder+ topBorder+rightBorder+ bottomBorder)
        canvas.clipRect(leftStart + leftBorder, topStart + topBorder, rightBorder, bottomBorder)
        canvas.drawBitmap(bitmap,  leftStart, topStart, paint)
        canvas.restore()
    }
}