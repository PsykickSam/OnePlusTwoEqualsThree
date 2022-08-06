package com.md.sadman.oneplustwoequalsthreeremake.animation

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.md.sadman.oneplustwoequalsthreeremake.R

/**
 * Animation storage is the base class for all the animations
 * with custom configurations
 *
 * @author Md. Sadman
 */
class AnimationStorage {

    companion object {

        fun aStartBgAnimation(animationDrawable: AnimationDrawable, enterFadeDuration: Int, exitFadeDuration: Int) {
            animationDrawable.setEnterFadeDuration(enterFadeDuration)
            animationDrawable.setExitFadeDuration(exitFadeDuration)
            animationDrawable.start()
        }

        fun aCycleFlipX0To90DegAt300s(context: Context): AnimatorSet {
            return AnimatorInflater.loadAnimator(context,
                R.animator.cycle_flip_x_0_90_deg_300ms
            ) as AnimatorSet
        }

        fun aCycleFlipX90To360DegAt500s(context: Context): AnimatorSet {
            return AnimatorInflater.loadAnimator(context,
                R.animator.cycle_flip_x_90_360_deg_500ms
            ) as AnimatorSet
        }

        fun aCycleFlipXFlow0To360Deg(context: Context): AnimatorSet {
            return AnimatorInflater.loadAnimator(context,
                R.animator.cycle_flip_x_flow_0_360_deg
            ) as AnimatorSet
        }

        fun aSlideStart(context: Context, duration: Long = 0) : Animation {
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_start)
            animation.duration = duration
            return animation
        }

        fun aSlideEnd(context: Context, duration: Long = 0) : Animation {
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_end)
            animation.duration = duration
            return animation
        }

    }

}