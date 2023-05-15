package com.gahov.musenergy.arch.provider

import com.gahov.musenergy.arch.coroutine.CoroutineLauncher

interface CoroutineProvider {
    val launcher: CoroutineLauncher
}