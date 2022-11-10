package com.example.gitemup.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.gitemup.common.events.EventObserver
import com.example.gitemup.common.events.UIEvent
import com.example.gitemup.common.extensions.navController
import com.example.gitemup.common.extensions.snackbar

abstract class BaseFragment<V : BaseViewModel> : Fragment() {

    abstract val viewModel: V


    @CallSuper
    protected open fun observe() {
        viewModel.uiEventLiveData.observe(viewLifecycleOwner, eventObserver)
    }

    private val eventObserver = EventObserver<UIEvent> { uiEvent -> handleEvent(uiEvent) }

    open fun handleEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.RemoveDestination -> navController?.popBackStack(uiEvent.destinationId, true)
            is UIEvent.BackTo -> navController?.popBackStack(uiEvent.destinationId, false)
            is UIEvent.PostExecutionMessage -> {
                snackbar(uiEvent.messageResId)
            }
            is UIEvent.NavigateTo -> navController?.navigate(uiEvent.action, uiEvent.bundle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }


}
