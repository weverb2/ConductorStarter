package com.brandonwever.android.conductorstarter.data

interface Reducer<S, A> {
    fun call(state: S, action: A): S
}