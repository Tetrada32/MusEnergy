package com.gahov.musenergy.arch.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gahov.architecture.core.ui.view.model.TextProvider
import com.gahov.domain.component.logger.Level
import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.failure.Failure
import com.gahov.musenergy.arch.component.error.ErrorHandler
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.arch.ktx.getString
import com.gahov.musenergy.arch.ktx.hideKeyboard
import com.gahov.musenergy.arch.provider.RouterProvider
import com.gahov.musenergy.arch.router.NavComponentRouter
import com.gahov.musenergy.arch.router.Router
import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.router.command.NavDirection
import com.gahov.musenergy.arch.ui.view.BaseView
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, T : ViewModel>(
    @LayoutRes private val contentLayoutID: Int,
    private val viewModelClass: Class<T>,
) : Fragment(), BaseView, RouterProvider {

    protected lateinit var binding: B
        private set

    protected lateinit var viewModel: T

    @Inject
    protected open lateinit var logger: Logger

    @Inject
    protected open lateinit var failureHandler: ErrorHandler

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val router: Router by lazy {
        NavComponentRouter(
            navController = findNavController(),
            logger = logger
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, contentLayoutID, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setBaseObservers()
        setObservers()
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    protected open fun navigate(command: Command) {
        when (command) {
            is NavDirection -> router.navigate(command)
            Command.Back -> router.popBackStack()
            Command.Root -> router.popToRoot()
            Command.Close -> requireActivity().finish()
            is Command.FeatureCommand -> navigateByFeature(command)
            is Command.Route -> commandError(command)
        }
    }

    protected open fun setBaseObservers() {
        getCurrentViewModel()?.let {
            it.errorEvent.observe(viewLifecycleOwner, ::displayError)
            it.navigationCommand.observe(viewLifecycleOwner, ::navigate)
            it.message.observe(viewLifecycleOwner, ::showMessage)
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

    open fun logMessage(message: TextProvider.Text) {
        logger.log(
            message = message.text
        )
    }

    protected open fun setObservers() {}

    protected open fun getCurrentViewModel(): BaseViewModel? {
        return null
    }

    override fun displayError(failure: Failure) {
        failureHandler.parseFailure(failure)
    }

    override fun showMessage(textProvider: TextProvider) {
        context?.let { context ->
            Toast.makeText(
                context.applicationContext,
                textProvider.getString(context),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}