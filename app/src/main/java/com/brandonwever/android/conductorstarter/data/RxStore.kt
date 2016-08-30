package com.brandonwever.android.conductorstarter.data

import rx.Observable
import rx.Scheduler
import rx.Subscription
import rx.functions.Func2
import rx.schedulers.Schedulers
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import java.util.concurrent.Executors

class RxStore<S, A> : Store<S, A> {
    private val dispatcher: SerializedSubject<A, A>
    private val state: Observable<S>
    private val sub: Subscription
    private var currentState: S
    private val reducer: Reducer<S, A>

    constructor(initialState: S, reducer: Reducer<S, A>, dedicated: Scheduler, pool: Scheduler) {
        this.reducer = reducer
        this.currentState = initialState
        this.dispatcher = PublishSubject.create<A>().toSerialized()
        this.currentState = initialState
        this.state = this.dispatcher.observeOn(dedicated).scan(initialState, Func2 { state, action ->
            currentState = reducer.call(state, action)
            return@Func2 currentState
        }).replay(1).observeOn(pool)
        this.sub = this.state.subscribe()
    }

    constructor(initialState: S, reducer: Reducer<S, A>) : this(initialState, reducer, Schedulers.from(Executors.newSingleThreadExecutor()), Schedulers.computation())

    override fun dispatch(action: A) {
        dispatcher.onNext(action)
    }

    override fun state(): Observable<S> {
        return state
    }

    override fun getCurrentState(): S {
        return currentState
    }
}