package com.gahov.musenergy.feature.main

interface BottomNavigationHost {

    var shouldShowBottomNavigation: Boolean

    fun showBottomNavigation(show: Boolean)
}