package com.brandonwever.android.conductorstarter.ui.productlist

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.brandonwever.android.conductorstarter.R
import com.brandonwever.android.conductorstarter.app.App
import com.brandonwever.android.conductorstarter.data.lcbo.LCBOActionCreator
import com.brandonwever.android.conductorstarter.data.redux.Action
import com.brandonwever.android.conductorstarter.data.redux.AppState
import com.brandonwever.android.conductorstarter.data.redux.RxStore
import com.brandonwever.android.conductorstarter.ui.util.EndlessScrollListener
import com.brandonwever.android.conductorstarter.ui.util.NavDrawerOwner
import com.brandonwever.android.conductorstarter.ui.util.PaginationDelegate
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.navigationIconResource
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class ProductListController : Controller(), PaginationDelegate {

    @Inject lateinit var navDrawerOwner: NavDrawerOwner
    @Inject lateinit var store: RxStore<AppState, Action>
    @Inject lateinit var actionCreator: LCBOActionCreator
    var productAdapter: ProductListingAdapter? = null

    init {
        App.graph.inject(this)
        actionCreator.getNewest()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        productAdapter = ProductListingAdapter()
        store.state().map { appState -> appState.lcboApiState }.distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(
                { lcboApiState ->
                    productAdapter?.products = lcboApiState.products
                    productAdapter?.notifyDataSetChanged()
                },
                { e -> Timber.e(e.message) })
        val view = SecondControllerView().createView(AnkoContext.create(inflater.context, this))
        return view
    }

    override fun onDestroyView(view: View?) {
        super.onDestroyView(view)
        productAdapter = null
    }

    fun onClick() {
        navDrawerOwner.openDrawer()
    }

    override fun loadMore() {
        actionCreator.getNextPage()
    }

    inner class SecondControllerView() : AnkoComponent<ProductListController> {
        val RECYCLER_VIEW = 1

        override fun createView(ui: AnkoContext<ProductListController>) = with(ui) {
            verticalLayout {
                toolbar() {
                    setTitleTextColor(ContextCompat.getColor(ctx, android.R.color.white))
                    title = "Product List"
                    backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    navigationIconResource = R.drawable.ic_menu
                    setNavigationOnClickListener({ onClick() })
                }
                recyclerView {
                    id = RECYCLER_VIEW
                    val linearLayoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                    adapter = productAdapter
                    layoutManager = linearLayoutManager
                    addOnScrollListener(EndlessScrollListener(linearLayoutManager, this@ProductListController))
                }
            }
        }
    }
}