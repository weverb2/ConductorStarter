package com.brandonwever.android.conductorstarter.data

import com.brandonwever.android.conductorstarter.data.redux.Reducer
import com.brandonwever.android.conductorstarter.data.redux.RxStore
import org.junit.Before
import org.junit.Test
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers

class RxStoreTest {

    @Before
    fun setUp() {

    }

    @Test
    fun testDispatch() {
        val testSubscriber = TestSubscriber<Int>()

        val initial = 0

        val after = 1

        val store = RxStore(initial, AdditionReducer(), Schedulers.immediate())

        store.state().subscribe(testSubscriber)
        store.dispatch("add")
        store.dispatch("subtract")

        testSubscriber.assertValues(initial, after, initial)
    }

    class AdditionReducer : Reducer<Int, String> {
        override fun call(state: Int, action: String): Int {
            when (action) {
                "add" -> return state + 1
                "subtract" -> return state - 1
            }
            return state
        }
    }
}