package com.gahov.musenergy.feature.stories.widget.storyviewer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import com.gahov.musenergy.R


class PausableProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var frontProgressView: View? = null
    private var maxProgressView: View? = null
    private var animation: PausableScaleAnimation? = null
    private var duration = DEFAULT_PROGRESS_DURATION
    private var callback: Callback? = null
    private var isStarted = false

    init {
        LayoutInflater.from(context).inflate(R.layout.pausable_progress, this)
        frontProgressView = findViewById(R.id.frontProgress)
        maxProgressView = findViewById(R.id.maxProgress)
    }

    fun setDuration(duration: Long) {
        this.duration = duration
        if (animation != null) {
            animation = null
            startProgress()
        }
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun setMax() {
        finishProgress(true)
    }

    fun setMin() {
        finishProgress(false)
    }

    private fun cancelAnimation() {
        animation?.let {
            it.setAnimationListener(null)
            it.cancel()
        }
    }

    fun setMinWithoutCallback() {
        maxProgressView?.setBackgroundResource(R.color.progressSecondary)
        maxProgressView?.visibility = View.VISIBLE
        cancelAnimation()
    }

    fun setMaxWithoutCallback() {
        maxProgressView?.setBackgroundResource(R.color.progressMaxActive)
        maxProgressView?.visibility = View.VISIBLE
        cancelAnimation()
    }

    private fun finishProgress(isMax: Boolean) {
        if (isMax) {
            maxProgressView?.setBackgroundResource(R.color.progressMaxActive)
        } else maxProgressView?.setBackgroundResource(
            R.color.progressSecondary
        )
        maxProgressView?.visibility = if (isMax) View.VISIBLE else View.GONE
        cancelAnimation()
        callback?.onFinishProgress()
    }

    fun startProgress() {
        maxProgressView?.visibility = View.GONE
        if (duration <= 0) duration = DEFAULT_PROGRESS_DURATION
        animation =
            PausableScaleAnimation(
                0f,
                1f,
                1f,
                1f,
                Animation.ABSOLUTE,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f
            )
        animation?.let { animation ->
            animation.duration = duration
            animation.interpolator = LinearInterpolator()
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    if (isStarted) {
                        return
                    }
                    isStarted = true
                    frontProgressView?.visibility = View.VISIBLE
                    callback?.onStartProgress()
                }

                override fun onAnimationEnd(animation: Animation) {
                    isStarted = false
                    callback?.onFinishProgress()
                }

                override fun onAnimationRepeat(animation: Animation) {
                    //NO-OP
                }
            })
            animation.fillAfter = true
            frontProgressView?.startAnimation(animation)
        }
    }

    fun pauseProgress() {
        animation?.pause()
    }

    fun resumeProgress() {
        animation?.resume()
    }

    fun clear() {
        if (animation != null) {
            animation!!.setAnimationListener(null)
            animation!!.cancel()
            animation = null
        }
    }

    interface Callback {
        fun onStartProgress()
        fun onFinishProgress()
    }

    companion object {
        private const val DEFAULT_PROGRESS_DURATION = 5000L
    }
}