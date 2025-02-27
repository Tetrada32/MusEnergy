package com.gahov.musenergy.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.navigation.NavigationBottomBarSectionsStateKeeper
import com.gahov.musenergy.common.extensions.hideAnimated
import com.gahov.musenergy.common.extensions.showAnimated
import com.gahov.musenergy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationHost {

    override var shouldShowBottomNavigation: Boolean = true

    lateinit var binding: ActivityMainBinding

    private var currentNavController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBinding()
        setupBottomNavigation(savedInstanceState)
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
            .also { it.lifecycleOwner = this }
    }

    private val navStateKeeper by lazy {
        NavigationBottomBarSectionsStateKeeper(
            activity = this,
            navHostContainerID = R.id.mainNavHost,
            navGraphIds = listOf(
                R.navigation.frontpage,
                R.navigation.favorites,
                R.navigation.search,
                R.navigation.profile,
            ),
            bottomNavigationViewID = R.id.bottomNavigation
        )
    }

    private fun setupBottomNavigation(savedInstanceState: Bundle?) {
        navStateKeeper.onCreate(savedInstanceState)
        navStateKeeper.getNavController()?.observe(this, ::handleCurrentNavController)
    }

    private fun handleCurrentNavController(navController: NavController?) {
        currentNavController = navController
    }

    override fun onBackPressed() {
        if (!navStateKeeper.onSupportNavigateUp())
            super.onBackPressed()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        navStateKeeper.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSupportNavigateUp() = navStateKeeper.onSupportNavigateUp()

    override fun showBottomNavigation(show: Boolean) {
        with(binding.bottomNavigation) {
            if (show) {
                showAnimated()
            } else {
                hideAnimated()
            }
        }
    }
}