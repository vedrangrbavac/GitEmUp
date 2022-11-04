package com.example.gitemup.common.events

data class Event<out T>(private val content: T) {

    private var handled = false

    private fun take(): T? {
        return if (handled) {
            null
        } else {
            handled = true
            content
        }
    }

    fun take(code: (T) -> Unit) {
        take()?.let(code)
    }

}
