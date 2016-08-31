package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.Action
import com.brandonwever.android.conductorstarter.data.AppState
import com.brandonwever.android.conductorstarter.data.RxStore
import com.brandonwever.android.conductorstarter.data.lcbo.model.Pager
import com.brandonwever.android.conductorstarter.data.lcbo.model.ProductResponse
import org.junit.Before
import org.junit.Test
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers

class LCBOReducerTest {

    lateinit var store: RxStore<AppState, Action>
    @Before
    fun setUp() {
        store = RxStore(AppState.INITIAL, LCBOReducer(), Schedulers.immediate())
    }

    @Test
    fun testCall() {
        val mockPager = Pager(1, 1, 1, false, true, 1, "", 1, "", 1, "", 1, "")
        val mockResponse = ProductResponse(1, "", mockPager, listOf(), "")
        val mockNextPager = Pager(2, 1, 1, false, true, 1, "", 1, "", 1, "", 1, "")
        val mockNextResponse = ProductResponse(1, "", mockNextPager, listOf(), "")
        val testSubscriber = TestSubscriber<AppState>()

        val initial = AppState.INITIAL

        val afterApiState = AppState.INITIAL.lcboApiState.copy(pager = mockPager)
        val after = initial.copy(lcboApiState = afterApiState)

        val afterNextApiState = afterApiState.copy(pager = mockNextPager)
        val afterNext = after.copy(lcboApiState = afterNextApiState)

        store.state().subscribe(testSubscriber)
        store.dispatch(LCBOProductNextResponse(mockResponse))
        store.dispatch(LCBOProductNextResponse(mockNextResponse))

        testSubscriber.assertValues(initial, after, afterNext)
    }
}