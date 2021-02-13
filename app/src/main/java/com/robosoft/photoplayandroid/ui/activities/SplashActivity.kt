package com.robosoft.photoplayandroid.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.robosoft.photoplayandroid.R
import com.robosoft.photoplayandroid.ui.base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }, DELAY_TIME
        )
    }
    override fun getLayout(): Int = R.layout.activity_splash

    companion object {
        const val DELAY_TIME = 3000L
    }
}
