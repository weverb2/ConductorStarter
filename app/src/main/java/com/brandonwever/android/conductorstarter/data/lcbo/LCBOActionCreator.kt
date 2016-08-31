package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.lcbo.interactor.LCBOInteractor
import com.brandonwever.android.conductorstarter.data.redux.Action
import com.brandonwever.android.conductorstarter.data.redux.AppState
import com.brandonwever.android.conductorstarter.data.redux.Store
import javax.inject.Inject

class LCBOActionCreator @Inject constructor(private val interactor: LCBOInteractor, private val store: Store<AppState, Action>) {
    fun getNewest() {
        interactor.getProducts(1).subscribe(
                { response -> store.dispatch(LCBOProductNewestResponse(response)) },
                { error -> store.dispatch(LCBOProductError(error)) })
    }

    fun getNextPage() {
        val nextPage = store.getCurrentState().lcboApiState.pager?.nextPage ?: 1
        interactor.getProducts(nextPage).subscribe(
                { response -> store.dispatch(LCBOProductNextResponse(response)) },
                { error -> store.dispatch(LCBOProductError(error)) })
    }
}