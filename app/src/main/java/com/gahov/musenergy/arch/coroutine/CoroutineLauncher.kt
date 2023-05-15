package com.gahov.musenergy.arch.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

interface CoroutineLauncher {
    fun launch(supervisor: Boolean = true, block: suspend CoroutineScope.() -> Unit): Job
}