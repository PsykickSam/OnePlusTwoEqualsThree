package com.md.sadman.oneplustwoequalsthreeremake.activity

import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.md.sadman.oneplustwoequalsthreeremake.R
import com.md.sadman.oneplustwoequalsthreeremake.helper.ActivityMenuHelper

/**
 * Meny activity, contains game menu
 *
 * @author Md. Sadman
 */
class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMenuHelper(this, DataBindingUtil.setContentView(this, R.layout.activity_menu))
    }

}
