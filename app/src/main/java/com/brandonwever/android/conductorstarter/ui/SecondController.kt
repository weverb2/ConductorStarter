package com.brandonwever.android.conductorstarter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.brandonwever.android.conductorstarter.app.App
import com.brandonwever.android.conductorstarter.data.Action
import com.brandonwever.android.conductorstarter.data.AppState
import com.brandonwever.android.conductorstarter.data.RxStore
import com.brandonwever.android.conductorstarter.data.lcbo.LCBOActionCreator
import com.brandonwever.android.conductorstarter.data.lcbo.LCBOInteractor
import org.jetbrains.anko.AnkoContext
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class SecondController : Controller, PaginationDelegate, View.OnClickListener {

    @Inject lateinit var navDrawerOwner: NavDrawerOwner
    @Inject lateinit var interactor: LCBOInteractor
    @Inject lateinit var store: RxStore<AppState, Action>
    @Inject lateinit var actionCreator: LCBOActionCreator
    var productAdapter: ProductListingAdapter? = null

    constructor() : super() {
        App.graph.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        productAdapter = ProductListingAdapter()
        store.state().map { appState -> appState.lcboApiState }.distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(
                { lcboApiState ->
                    productAdapter?.products = lcboApiState.products
                    productAdapter?.notifyDataSetChanged()
                },
                { e -> Timber.e(e.message) })
        actionCreator.getNewest()
        val view = SecondControllerView(this, productAdapter!!, this).createView(AnkoContext.Companion.create(inflater.context, this))
        return view
    }

    override fun onDestroyView(view: View?) {
        super.onDestroyView(view)
        productAdapter = null
    }

    override fun onClick(v: View) {
        navDrawerOwner.openDrawer()
    }

    override fun loadMore() {
        actionCreator.getNextPage()
    }
}