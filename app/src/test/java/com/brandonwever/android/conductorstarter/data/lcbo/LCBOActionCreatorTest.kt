package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.lcbo.interactor.LCBOInteractor
import com.brandonwever.android.conductorstarter.data.lcbo.model.Pager
import com.brandonwever.android.conductorstarter.data.lcbo.model.ProductResponse
import com.brandonwever.android.conductorstarter.data.redux.Action
import com.brandonwever.android.conductorstarter.data.redux.AppState
import com.brandonwever.android.conductorstarter.data.redux.Store
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Single

class LCBOActionCreatorTest {

    @Test
    fun testGetNewest() {
        val mockProductResponse = ProductResponse(message = "success");
        val mockStore = mock<Store<AppState, Action>>()
        val mockInteractor = mock<LCBOInteractor>()

        whenever(mockInteractor.getProducts(1)).thenReturn(Single.just(mockProductResponse))
        doNothing().whenever(mockStore).dispatch(any<LCBOProductNewestResponse>())

        val actionCreator = LCBOActionCreator(mockInteractor, mockStore)

        actionCreator.getNewest()

        verify(mockInteractor, times(1)).getProducts(1)
        verify(mockStore, mode = times(1)).dispatch(any<LCBOProductNewestResponse>())
    }

    @Test
    fun testGetNewestError() {
        val mockStore = mock<Store<AppState, Action>>()
        val mockInteractor = mock<LCBOInteractor>()

        whenever(mockInteractor.getProducts(1)).thenReturn(Single.error(RuntimeException("boom")))
        doNothing().whenever(mockStore).dispatch(any<LCBOProductError>())

        val actionCreator = LCBOActionCreator(mockInteractor, mockStore)

        actionCreator.getNewest()

        verify(mockInteractor, times(1)).getProducts(1)
        verify(mockStore, mode = times(1)).dispatch(any<LCBOProductError>())
    }

    @Test
    fun testGetNextPage() {
        val mockPager = Pager(nextPage = 2)
        val mockProductResponse = ProductResponse(message = "success");
        val mockStore = mock<Store<AppState, Action>>()
        val mockInteractor = mock<LCBOInteractor>()

        val after = AppState.INITIAL.copy(lcboApiState = LCBOApiState(pager = mockPager))

        whenever(mockStore.getCurrentState()).thenReturn(after)
        whenever(mockInteractor.getProducts(2)).thenReturn(Single.just(mockProductResponse))
        doNothing().whenever(mockStore).dispatch(any<LCBOProductNextResponse>())

        val actionCreator = LCBOActionCreator(mockInteractor, mockStore)

        actionCreator.getNextPage()

        verify(mockInteractor, times(1)).getProducts(2)
        verify(mockStore, mode = times(1)).dispatch(any<LCBOProductNextResponse>())
    }

    @Test
    fun testGetNextPageError() {
        val mockPager = Pager(nextPage = 2)
        val mockProductResponse = ProductResponse(message = "success");
        val mockStore = mock<Store<AppState, Action>>()
        val mockInteractor = mock<LCBOInteractor>()

        val after = AppState.INITIAL.copy(lcboApiState = LCBOApiState(pager = mockPager))

        whenever(mockStore.getCurrentState()).thenReturn(after)
        whenever(mockInteractor.getProducts(2)).thenReturn(Single.just(mockProductResponse))
        doNothing().whenever(mockStore).dispatch(any<LCBOProductNextResponse>())

        val actionCreator = LCBOActionCreator(mockInteractor, mockStore)

        actionCreator.getNextPage()

        verify(mockInteractor, times(1)).getProducts(2)
        verify(mockStore, mode = times(1)).dispatch(any<LCBOProductNextResponse>())
    }
}