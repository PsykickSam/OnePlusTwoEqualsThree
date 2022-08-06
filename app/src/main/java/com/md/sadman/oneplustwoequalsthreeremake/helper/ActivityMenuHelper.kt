package com.md.sadman.oneplustwoequalsthreeremake.helper

import android.animation.AnimatorSet
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.Looper
import com.md.sadman.oneplustwoequalsthreeremake.activity.GameActivity
import com.md.sadman.oneplustwoequalsthreeremake.animation.AnimationStorage
import com.md.sadman.oneplustwoequalsthreeremake.databinding.ActivityMenuBinding

/**
 * Activity helper class contains all the helping methods and
 * use for combining data binding usage
 *
 * @author Md. Sadman
 */
class ActivityMenuHelper(private var context: Context, private var binding: ActivityMenuBinding) {

    private lateinit var tvTitleTextBottomCycleAnimation: AnimatorSet

    init {
        binding.tvTitleTextBottom.cameraDistance = 15000 * context.resources.displayMetrics.density

        this.initAnimations()
        this.logoAnimationLoop()
        this.attachClickEvents()
    }

    private fun initAnimations() {
        // Background Animation
        AnimationStorage.aStartBgAnimation(this.binding.avMenuActivity.background as AnimationDrawable, 500, 2000)

        // View animation
        binding.tvTitleTextTop.startAnimation(AnimationStorage.aSlideStart(context, 500))
        binding.tvTitleTextBottom.startAnimation(AnimationStorage.aSlideEnd(context, 500))
        binding.btnPlay.startAnimation(AnimationStorage.aSlideStart(context, 500))
        binding.btnSettings.startAnimation(AnimationStorage.aSlideEnd(context, 500))
        binding.btnExit.startAnimation(AnimationStorage.aSlideStart(context, 500))

        // Initialize animation
        tvTitleTextBottomCycleAnimation = AnimationStorage.aCycleFlipXFlow0To360Deg(context)
    }

    private fun logoAnimationLoop() {
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.postDelayed(object : Runnable {
            override fun run() {
                tvTitleTextBottomCycleAnimation.setTarget(binding.tvTitleTextBottom)
                tvTitleTextBottomCycleAnimation.start()

                mainHandler.postDelayed(this, 5000)
            }
        }, 1500)
    }

    private fun attachClickEvents() {
        this.binding.btnPlay.setOnClickListener { btnPlayClickEvent() }
    }

    private fun btnPlayClickEvent() {
        context.startActivity(Intent(context, GameActivity::class.java))
    }
}