package com.robosoft.photoplayandroid.ui.activities

import android.os.Bundle
import com.robosoft.photoplayandroid.R
import com.robosoft.photoplayandroid.ui.base.BaseActivity
import com.robosoft.photoplayandroid.ui.fragments.HomeFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
    }

    override fun getLayout(): Int = R.layout.activity_main


    private fun setUpView() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, HomeFragment())
            .commit()
    }

}
