package com.mredrock.cyxbs.freshman.customview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.mredrock.cyxbs.freshman.R

/**
 * Created by tk on 2019/8/10
 * 动画打钩自定义view
 *
 */
class HookView : View {
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private var mPathhook: Path? = null
    private var dst: Path? = null
    private var mProgress: Float = 0.toFloat()
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var measure: PathMeasure? = null
    private var currentValue: Float = 0.toFloat()
    //当前状态
    private var state = CIRCLE

    //插值器
    private var mAnimatorCircle: ValueAnimator? = null
    private var mAnimatorHook: ValueAnimator? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        initPaint()
        initPath()
        initAnimator()
    }

    private fun initPaint() {
        mPaint = Paint()
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = 8f
        mPaint!!.isAntiAlias = true
        mPaint!!.color = resources.getColor(R.color.colorPrimaryDark)
        mPaint!!.strokeCap = Paint.Cap.ROUND
        mPaint!!.strokeJoin = Paint.Join.ROUND
        measure = PathMeasure()
    }

    private fun initPath() {
        mPath = Path()
        mPathhook = Path()
        dst = Path()

    }

    private fun initAnimator() {
        mAnimatorCircle = ValueAnimator.ofFloat(0f, 359.5f).setDuration(1000)
        mAnimatorHook = ValueAnimator.ofFloat(0f, 1f).setDuration(700)
        mAnimatorCircle!!.addUpdateListener { animation ->
            mProgress = animation.animatedValue as Float
            mPath!!.reset()
            mPath!!.addArc(
                (-mWidth / 2 + 5).toFloat(),
                (-mHeight / 2 + 5).toFloat(),
                (mWidth / 2 - 5).toFloat(),
                (mHeight / 2 - 5).toFloat(),
                90f,
                mProgress
            )
            postInvalidate()
        }

        mAnimatorCircle!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mAnimatorHook!!.start()
                state = 1
            }
        })

        mAnimatorHook!!.addUpdateListener { animation ->
            currentValue = animation.animatedValue as Float
            postInvalidate()
        }
        mAnimatorCircle!!.start()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        //设置打钩的路线
        mPathhook!!.moveTo((-w / 3.5).toFloat(), 0f)
        mPathhook!!.lineTo((-w / 12).toFloat(), (h / 4).toFloat())
        mPathhook!!.lineTo((w / 4).toFloat(), (-h / 7).toFloat())
        measure!!.setPath(mPathhook, false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate((mWidth / 2).toFloat(), (mHeight / 2).toFloat())
        canvas.drawPath(mPath!!, mPaint!!)
        if (state == 1) {
            measure!!.getSegment(0f, measure!!.length * currentValue, dst, true)
            canvas.drawPath(dst!!, mPaint!!)
        }
    }
    companion object {
        //状态：画圆和打钩
        var CIRCLE = 0
        var HOOK = 1
    }
}

