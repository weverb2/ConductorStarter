package com.brandonwever.android.conductorstarter.data.redux

import rx.Observable

interface Store<S, A> {
    fun dispatch(action: A)

    fun state(): Observable<S>

    fun getCurrentState(): S
}