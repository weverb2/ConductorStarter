package com.brandonwever.android.conductorstarter.data

import javax.inject.Inject

class CombineReducers<S, A> @Inject constructor(vararg val reducers: Reducer<S, A>) : Reducer<S, A> {
    override fun call(state: S, action: A): S {
        var newState = state
        for (reducer: Reducer<S, A> in reducers) {
//            Timber.d(reducer.javaClass.simpleName)
//            Timber.d(newState.toString())
            newState = reducer.call(newState, action)
        }
        return newState
    }

}