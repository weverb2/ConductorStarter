package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.lcbo.model.Pager
import com.brandonwever.android.conductorstarter.data.lcbo.model.ProductResponse
import com.brandonwever.android.conductorstarter.data.redux.Action
import com.brandonwever.android.conductorstarter.data.redux.AppState
import com.brandonwever.android.conductorstarter.data.redux.RxStore
import org.junit.Before
import org.junit.Test
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers

class LCBOReducerTest {

    lateinit var store: RxStore<AppState, Action>
    lateinit var testSubscriber: TestSubscriber<AppState>
    @Before
    fun setUp() {
        testSubscriber = TestSubscriber<AppState>()
        store = RxStore(AppState.INITIAL, LCBOReducer(), Schedulers.immediate())
    }

    @Test
    fun testGetNewest() {
        val mockPager = Pager(currentPage = 1)
        val mockNewestResponse = ProductResponse(pager = mockPager)

        val initial = AppState.INITIAL

        val after = initial.copy(lcboApiState = LCBOApiState(pager = mockPager))

        store.state().subscribe(testSubscriber)
        store.dispatch(LCBOProductNewestResponse(mockNewestResponse))

        testSubscriber.assertValues(initial, after)
    }

    @Test
    fun testGetNewestWithData() {
        val mockPager = Pager(currentPage = 1)
        val newMockPager = Pager(currentPageRecordCount = 200)
        val mockNewestResponse = ProductResponse(pager = newMockPager)

        val initial = AppState.INITIAL.copy(lcboApiState = LCBOApiState(pager = mockPager))

        val after = initial.copy(lcboApiState = initial.lcboApiState.copy(pager = newMockPager))

        store = RxStore(initial, LCBOReducer(), Schedulers.immediate())
        store.state().subscribe(testSubscriber)
        store.dispatch(LCBOProductNewestResponse(mockNewestResponse))

        testSubscriber.assertValues(initial, after)
    }

    @Test
    fun testGetNext() {
        val mockPager = Pager(currentPage = 1)
        val mockResponse = ProductResponse(pager = mockPager)
        val mockNextPager = Pager(currentPage = 2)
        val mockNextResponse = ProductResponse(pager = mockNextPager)
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