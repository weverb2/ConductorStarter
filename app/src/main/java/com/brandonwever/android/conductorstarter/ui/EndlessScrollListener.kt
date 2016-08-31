package com.brandonwever.android.conductorstarter.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class EndlessScrollListener(val layoutManager: LinearLayoutManager, val delegate: PaginationDelegate) : RecyclerView.OnScrollListener() {
    private val VISIBLE_THRESHOLD = 5
    private var previousTotal = 0
    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = recyclerView!!.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
        if (loading && totalItemCount > previousTotal || previousTotal > totalItemCount) {
            loading = false
            previousTotal = totalItemCount
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + VISIBLE_THRESHOLD) {
            loading = true
            delegate.loadMore()
        }
    }
}