package com.robosoft.photoplayandroid.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.robosoft.photoplayandroid.R
import com.robosoft.photoplayandroid.ui.base.BaseActivity
import com.robosoft.photoplayandroid.ui.fragments.HomeFragment
import com.robosoft.photoplayandroid.ui.viewModels.BannerViewModel
import com.robosoft.photoplayandroid.utils.ScreenUtils
import com.robosoft.photoplayandroid.utils.Status
import com.robosoft.photoplayandroid.utils.setImage
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val bannerViewModel: BannerViewModel by viewModel()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        setAppBarOffsetListener()
        setBannerImage()
        bannerViewModel.getBannerImage()
    }

    override fun getLayout(): Int = R.layout.activity_main


    private fun setUpView() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, HomeFragment())
            .commit()
    }

    private fun setAppBarOffsetListener() {
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    collapsingToolbar
                )
            ) {
                logoPinned.visibility = View.VISIBLE
                menu?.getItem(0)?.isVisible = true
            } else {
                logoPinned.visibility = View.GONE
                menu?.getItem(0)?.isVisible = false
            }
        })
    }

    private fun setBannerImage() {
        bannerViewModel.bannerImage.observe(this, { result ->
            if (result.status == Status.SUCCESS) {
                val imageUrl = result.data?.photos?.get(0)?.src
                imageUrl?.let {
                    val screenType = ScreenUtils.getScreenType(this)
                    if (screenType == ScreenUtils.ScreenSize.LANDSCAPE)
                        bannerImage.setImage(imageUrl.landscape)
                    else
                        bannerImage.setImage(imageUrl.portrait)
                }
            }
        })
        bannerViewModel.networkError.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        this.menu = menu
        return true
    }
}
