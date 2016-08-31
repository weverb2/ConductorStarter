package com.brandonwever.android.conductorstarter.data.redux

interface Reducer<S, A> {
    fun call(state: S, action: A): S
}