package team.hackm.android.qolk.component.splash

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import com.gelitenight.waveview.library.WaveView
import java.util.*

/**
 * Created by shunhosaka on 2016/02/14.
 */
class WaveHelper(private val mWaveView: WaveView, val eventListener: WaveAnimationEventListener? = null) {

    private var mAnimatorSet: AnimatorSet? = null

    init {
        initAnimation()
    }

    fun start() {
        mWaveView.isShowWave = true
        if (mAnimatorSet != null) {
            mAnimatorSet!!.start()
        }
    }

    private fun initAnimation() {
        val animators = ArrayList<Animator>()

        // horizontal animation.
        // wave waves infinitely.
        val waveShiftAnim = ObjectAnimator.ofFloat(
                mWaveView, "waveShiftRatio", 0f, 1f)
        waveShiftAnim.repeatCount = ValueAnimator.INFINITE
        waveShiftAnim.duration = 1000
        waveShiftAnim.interpolator = LinearInterpolator()
        animators.add(waveShiftAnim)

        // vertical animation.
        // water level increases from 0 to center of WaveView
        val waterLevelAnim = ObjectAnimator.ofFloat(mWaveView, "waterLevelRatio", 0f, 1.1f)
        waterLevelAnim.duration = 3400
        waterLevelAnim.startDelay = 800
        waterLevelAnim.interpolator = DecelerateInterpolator()
        waterLevelAnim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animator: Animator?) {
            }

            override fun onAnimationEnd(animator: Animator?) {
                eventListener?.onVerticalAnimationEnd()
            }

            override fun onAnimationCancel(animator: Animator?) {
            }

            override fun onAnimationStart(animator: Animator?) {
                mWaveView.visibility = View.VISIBLE
            }

        })
        animators.add(waterLevelAnim)

        // amplitude animation.
        // wave grows big then grows small, repeatedly
        val amplitudeAnim = ObjectAnimator.ofFloat(
                mWaveView, "amplitudeRatio", 0.0001f, 0.05f)
        amplitudeAnim.repeatCount = ValueAnimator.INFINITE
        amplitudeAnim.repeatMode = ValueAnimator.REVERSE
        amplitudeAnim.duration = 5000
        amplitudeAnim.interpolator = LinearInterpolator()
        animators.add(amplitudeAnim)

        mAnimatorSet = AnimatorSet()
        mAnimatorSet!!.playTogether(animators)
    }

    fun cancel() {
        if (mAnimatorSet != null) {
            // mAnimatorSet.cancel();
            mAnimatorSet!!.end()
        }
    }

    public interface WaveAnimationEventListener {
        public fun onVerticalAnimationEnd()
    }

}