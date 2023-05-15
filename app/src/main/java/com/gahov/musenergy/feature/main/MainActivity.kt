package com.gahov.musenergy.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.navigation.NavigationBottomBarSectionsStateKeeper
import com.gahov.musenergy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var currentNavController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation(savedInstanceState)
    }

    private val navStateKeeper by lazy {
        NavigationBottomBarSectionsStateKeeper(
            activity = this,
            navHostContainerID = R.id.mainNavHost,
            navGraphIds = listOf(
                R.navigation.frontpage,
                R.navigation.search,
                R.navigation.favorites,
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
}