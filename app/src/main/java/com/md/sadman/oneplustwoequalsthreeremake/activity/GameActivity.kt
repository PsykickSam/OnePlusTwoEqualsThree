package com.md.sadman.oneplustwoequalsthreeremake.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.md.sadman.oneplustwoequalsthreeremake.R
import com.md.sadman.oneplustwoequalsthreeremake.helper.ActivityGameHelper

/**
 * Game activity, contains the main game structure, events and game logic
 *
 * @author Md. Sadman
 */
class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityGameHelper(this, DataBindingUtil.setContentView(this, R.layout.activity_game))
    }

}
