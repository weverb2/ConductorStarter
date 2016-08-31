package com.brandonwever.android.conductorstarter.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.brandonwever.android.conductorstarter.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.navigationIconResource
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

class SecondControllerView(val paginationDelegate: PaginationDelegate, val listAdapter: ProductListingAdapter, val navigationClickListener: View.OnClickListener) : AnkoComponent<SecondController> {
    companion object {
        val RECYCLER_VIEW = 1
    }

    override fun createView(ui: AnkoContext<SecondController>) = with(ui) {
        verticalLayout {
            toolbar() {
                setTitleTextColor(ContextCompat.getColor(ctx, android.R.color.white))
                title = "Second Controller"
                backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                navigationIconResource = R.drawable.ic_menu
                setNavigationOnClickListener(navigationClickListener)
            }
            recyclerView {
                id = RECYCLER_VIEW
                val linearLayoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                adapter = listAdapter
                layoutManager = linearLayoutManager
                addOnScrollListener(EndlessScrollListener(linearLayoutManager, paginationDelegate))
            }
        }
    }
}
