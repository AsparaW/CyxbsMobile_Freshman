package com.mredrock.cyxbs.freshman.customview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by tk on 2019/8/12
 *饼状图自定义view
 */

/**
 * 百分数转小数
 */
fun percentageToDecimal(num: String): Float {
    if (num.contains("%")) {
        val number = num.replace("%", "")
        return number.toFloat()/100
    } else {
        return 0f
    }
}

class PieChart : View {

    lateinit var name: String //数据:学院名，男生比例，女生比例
    lateinit var boy: String
    lateinit var girl: String
    var width: Int? = 0
    var height: Int? = 0
    var paint: Paint? = null
    var mpaint: Paint? = null
    var process: Float? = 0f
    var mState: Int = 0
    var rect: RectF? = null
    //动画
    var animator : ValueAnimator? = null
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    fun init() {
        paint = Paint()
        mpaint = Paint()
        paint?.apply {
            color = Color.BLACK
            textSize = 30f
            isAntiAlias = true
        }
        mpaint?.apply {
            isAntiAlias = true
        }
        mpaint?.setARGB(255,113,213,255)

        rect = RectF(109f,162f,459f,512f)
    }

    fun setData(name: String,boy: String,girl: String) {
        this.name = name
        this.boy = boy
        this.girl = girl
        invalidate()
        animator = ValueAnimator.ofFloat(0f,360f).setDuration(1000)
        animator?.apply {
            addUpdateListener {
                process = it.animatedValue as Float?

                postInvalidate()
            }

            //动画结束时绘制文字
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mState = 1
                }
            })

            start()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
    }

    override fun onDraw(canvas: Canvas?) {

        paint?.color = Color.BLACK
        mpaint?.setARGB(255,113,213,255)
        canvas?.drawText("${name}男女比例", 0f, 29f, paint)
        canvas?.drawRect(0f, 61f, 46f, 91f, mpaint)
        mpaint?.setARGB(255,255,149,195)
        canvas?.drawRect(0f,112f,46f,142f,mpaint)
        paint?.setARGB(255,92,127,252)
        paint?.textSize = 26f
        canvas?.drawText("男",55f,88f,paint)
        canvas?.drawText("女",55f,138f,paint)

        if (process!! <= percentageToDecimal(girl)*360){
            canvas?.drawArc(rect,90f, process!!,true,mpaint)
        }
    }




}