package com.md.sadman.oneplustwoequalsthreeremake.helper

import android.animation.AnimatorSet
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.widget.Toast
import androidx.core.animation.addListener
import com.md.sadman.oneplustwoequalsthreeremake.animation.AnimationStorage
import com.md.sadman.oneplustwoequalsthreeremake.databinding.ActivityGameBinding
import com.md.sadman.oneplustwoequalsthreeremake.lib.Equation
import com.md.sadman.oneplustwoequalsthreeremake.model.EquationData
import java.util.concurrent.atomic.AtomicInteger

/**
 * Activity helper class contains all the helping methods and
 * use for combining data binding usage
 *
 * @author Md. Sadman
 */
class ActivityGameHelper(private var context: Context, private var binding: ActivityGameBinding) {

    private val SCORE_DEFAULT: Int = 3

    private lateinit var clFormulaAnimLayoutBeforeAnim: AnimatorSet
    private lateinit var clFormulaAnimLayoutAfterAnim: AnimatorSet

    private var listOfEquations: List<EquationData>
    private var listOfEquationIndex: AtomicInteger
    private var score: AtomicInteger

    init {
        listOfEquations = Equation.generateEquation(3, 10)
        listOfEquationIndex = AtomicInteger(0)
        score =  AtomicInteger(0)

        this.binding.tvEquationText.cameraDistance = 8000 * this.context.resources.displayMetrics.density
        this.binding.tvEquationText.text = listOfEquations.get(this.listOfEquationIndex.get()).equation

        this.initAnimations()
        this.initAnimationProps()
        this.attachClickEvents()
    }

    fun btnOneClickEvent() {
        btnEvent(1)
    }

    fun btnTwoClickEvent() {
        btnEvent(2)
    }

    fun btnThreeClickEvent() {
        btnEvent(3)
    }

    private fun btnEvent(key: Int) {
        if (listOfEquationIndex.get() >= listOfEquations.size) {
            Toast.makeText(this.context, "WIN", Toast.LENGTH_SHORT).show()
        } else if (listOfEquations.get(listOfEquationIndex.get()).result != key) {
            Toast.makeText(this.context, "FALSE", Toast.LENGTH_SHORT).show()
        } else {
            this.clFormulaAnimLayoutBeforeAnim.setTarget(this.binding.clFormulaAnimLayout)
            this.clFormulaAnimLayoutBeforeAnim.start()
        }
    }

    private fun initAnimations() {
        // Background Animation
        AnimationStorage.aStartBgAnimation(this.binding.avGameActivity.background as AnimationDrawable, 500, 2000)

        // Other Animations
        clFormulaAnimLayoutBeforeAnim = AnimationStorage.aCycleFlipX0To90DegAt300s(this.context)
        clFormulaAnimLayoutAfterAnim = AnimationStorage.aCycleFlipX90To360DegAt500s(this.context)
    }

    private fun initAnimationProps() {
        // Animation Listeners
        clFormulaAnimLayoutBeforeAnim.addListener(onEnd = {
            if (listOfEquationIndex.incrementAndGet() < listOfEquations.size) {
                this.binding.tvEquationText.text = listOfEquations.get(listOfEquationIndex.get()).equation
                this.binding.tvScoreText.text = score.addAndGet(SCORE_DEFAULT).toString()
                clFormulaAnimLayoutAfterAnim.setTarget(this.binding.clFormulaAnimLayout)
                clFormulaAnimLayoutAfterAnim.start()
            } else {
                Toast.makeText(this.context, "WIN", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun attachClickEvents() {
        this.binding.btnOne.setOnClickListener { btnOneClickEvent() }
        this.binding.btnTwo.setOnClickListener { btnTwoClickEvent() }
        this.binding.btnThree.setOnClickListener { btnThreeClickEvent() }
    }

}