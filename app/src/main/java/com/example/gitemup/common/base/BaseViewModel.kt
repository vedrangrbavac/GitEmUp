package com.example.gitemup.common.base


import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitemup.common.errors.ErrorHandler
import com.example.gitemup.common.events.EventLiveData
import com.example.gitemup.common.events.UIEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val defaultErrorHandler = CoroutineExceptionHandler { _, throwable ->
        val messageResId = ErrorHandler.resolveExceptionMessageId(throwable)
        notifyEvent(UIEvent.PostExecutionMessage(messageResId))
    }

    val uiEventLiveData = EventLiveData<UIEvent>()


    fun <T> suspendCall(
        errorHandler: CoroutineExceptionHandler = defaultErrorHandler,
        block: suspend () -> T
    ) {
        viewModelScope.launch(errorHandler) {
            block()
        }
    }

    protected fun notifyEvent(uiEvent: UIEvent) {
        uiEventLiveData.postEventValue(uiEvent)
    }

    protected fun navigate(@IdRes action: Int, bundle: Bundle? = null) {
        notifyEvent(UIEvent.NavigateTo(action, bundle))
    }

    protected fun backTo(destinationId: Int) {
        notifyEvent(UIEvent.BackTo(destinationId))
    }

    protected fun removeDestination(destinationId: Int) {
        notifyEvent(UIEvent.RemoveDestination(destinationId))
    }

    protected fun setMessage(messageId: Int) {
        notifyEvent(UIEvent.PostExecutionMessage(messageId))
    }


}
