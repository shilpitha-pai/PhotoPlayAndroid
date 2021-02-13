package com.robosoft.photoplayandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robosoft.photoplayandroid.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int =R.layout.activity_main
}