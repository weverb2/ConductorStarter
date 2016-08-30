package com.brandonwever.android.conductorstarter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.brandonwever.android.conductorstarter.app.App
import com.brandonwever.android.conductorstarter.data.Store
import com.brandonwever.android.conductorstarter.data.lcbo.LCBOApiState
import com.brandonwever.android.conductorstarter.data.lcbo.LCBOInteractor
import com.brandonwever.android.conductorstarter.data.lcbo.model.Pager
import org.jetbrains.anko.AnkoContext
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class SecondController : Controller, PaginationDelegate, View.OnClickListener {

    @Inject lateinit var navDrawerOwner: NavDrawerOwner
    @Inject lateinit var interactor: LCBOInteractor
    @Inject lateinit var store: Store
    var productAdapter: ProductListingAdapter? = null
    var pager: Pager? = null

    constructor() : super() {
        App.graph.inject(this)
        loadNewest()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        productAdapter = ProductListingAdapter()
        productAdapter!!.products = store.state.lcboApiState.products
        pager = store.state.lcboApiState.pager
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

    fun loadNewest() {
        pager = null
        loadMore()
    }

    override fun loadMore() {
        val nextPage = pager?.nextPage ?: 1
        interactor.getProducts(nextPage).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { response ->
                    val newList = store.state.lcboApiState.products + response.result
                    store.state = store.state.copy(lcboApiState = LCBOApiState(newList, response.pager))
                    productAdapter!!.products = store.state.lcboApiState.products
                    productAdapter!!.notifyDataSetChanged()
                    pager = store.state.lcboApiState.pager
                },
                { error -> Timber.e(error.message) })
    }
}