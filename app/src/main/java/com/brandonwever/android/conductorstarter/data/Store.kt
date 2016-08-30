package com.brandonwever.android.conductorstarter.data

import rx.Observable

interface Store<S, A> {
    fun dispatch(action: A)

    fun state(): Observable<S>

    fun getCurrentState(): S
}