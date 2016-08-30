package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.Action
import com.brandonwever.android.conductorstarter.data.AppState
import com.brandonwever.android.conductorstarter.data.RxStore
import javax.inject.Inject

class LCBOActionCreator @Inject constructor(private val interactor: LCBOInteractor, private val store: RxStore<AppState, Action>) {
    fun getNewest() {
        interactor.api.getProducts(1).subscribe(
                { response -> store.dispatch(LCBOProductNewestResponse(response)) },
                { error -> store.dispatch(LCBOProductError(error)) })
    }

    fun getNextPage() {
        val nextPage = store.getCurrentState().lcboApiState.pager?.nextPage ?: 1
        interactor.api.getProducts(nextPage).subscribe(
                { response -> store.dispatch(LCBOProductNextResponse(response)) },
                { error -> store.dispatch(LCBOProductError(error)) })
    }
}