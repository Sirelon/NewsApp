package com.sirelon.newsapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Event, Effect> : ViewModel() {

    private val _state = MutableStateFlow<State>(initialState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<Effect>()
    val effects = _effects.asSharedFlow()

    protected abstract fun initialState(): State

    abstract fun onEvent(event: Event)

    protected fun setState(function: (State) -> State) {
        _state.update(function)
    }

    protected fun postEffect(effect: Effect) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }
}
