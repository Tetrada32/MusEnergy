package com.gahov.musenergy.arch.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.gahov.musenergy.arch.ui.view.BaseView
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.domain.component.logger.Level
import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.failure.Failure
import com.gahov.musenergy.arch.component.error.ErrorHandler
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.arch.coroutine.impl.DefaultCoroutineLauncher
import com.gahov.musenergy.arch.ktx.getString
import com.gahov.musenergy.arch.provider.CoroutineProvider
import com.gahov.musenergy.arch.provider.RouterProvider
import com.gahov.musenergy.arch.router.NavComponentRouter
import com.gahov.musenergy.arch.router.Router
import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.router.command.NavDirection
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val contentLayoutID: Int
) : AppCompatActivity(), BaseView, RouterProvider, CoroutineProvider {

    protected lateinit var binding: T
        private set

    protected abstract val navController: NavController

    @Inject
    private lateinit var logger: Logger

    @Inject
    private lateinit var failureHandler: ErrorHandler

    override val router: Router by lazy {
        NavComponentRouter(
            navController = navController,
            logger = logger
        )
    }

    override val launcher by lazy { DefaultCoroutineLauncher(lifecycleScope) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, contentLayoutID)
        binding.lifecycleOwner = this
        showSystemUI()
        initBaseObservers()
        setObservers()
    }

    protected open fun initBaseObservers() {
        getViewModel()?.let {
            it.errorEvent.observe(this, ::displayError)
            it.navigationCommand.observe(this, ::navigate)
            it.message.observe(this, ::showMessage)
        }
    }

    protected open fun setObservers() {}

    protected open fun navigate(command: Command) {
        when (command) {
            is NavDirection -> router.navigate(command)
            Command.Back -> router.popBackStack()
            Command.Root -> router.popToRoot()
            Command.Close -> finish()
            is Command.FeatureCommand -> navigateByFeature(command)
            is Command.Route -> commandError(command)
        }
    }

    protected open fun navigateByFeature(command: Command.FeatureCommand) {
        logger.log(
            level = Level.Warning,
            message = "method navigateByFeature isn't implement for $command"
        )
    }

    private fun commandError(command: Command) {
        logger.log(
            level = Level.Warning,
            message = "navigation isn't implement for $command"
        )
    }

    // Deprecated for Android 10
    @Suppress("DEPRECATION")
    protected fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    // Deprecated for Android 10
    @Suppress("DEPRECATION")
    protected fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun displayError(failure: Failure) {
        failureHandler.parseFailure(failure)
    }

    override fun showMessage(textProvider: TextProvider) {
        Toast.makeText(applicationContext, textProvider.getString(this), Toast.LENGTH_SHORT).show()
    }

    protected open fun getViewModel(): BaseViewModel? {
        return null
    }
}
