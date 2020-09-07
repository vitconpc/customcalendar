@file:Suppress("unused")
package jp.mcpass.okanenocompass.base

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class RVLoadMoreListener constructor(
        private val layoutManager: RecyclerView.LayoutManager?,
        private val onLoadMore: (currentPage: Int) -> Unit
) : RecyclerView.OnScrollListener() {

    var currentPage = 1

    private var loading = true

    private var previousTotalItemCount = 0

    private val startingPageIndex = 1

    private val lastVisibleItemPosition: Int
        get() {
            var lastVisibleItemPosition = 0
            when (layoutManager) {
                is StaggeredGridLayoutManager -> {
                    val lastVisibleItemPositions = layoutManager
                            .findLastVisibleItemPositions(
                                    null)
                    lastVisibleItemPosition = getLastVisibleItem(
                            lastVisibleItemPositions)
                }
                is GridLayoutManager -> lastVisibleItemPosition = layoutManager
                        .findLastVisibleItemPosition()
                is LinearLayoutManager -> lastVisibleItemPosition = layoutManager
                        .findLastVisibleItemPosition()
            }
            return lastVisibleItemPosition
        }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(view, dx, dy)
        if (layoutManager == null) return
        val totalItemCount = this.layoutManager.itemCount
        val lastVisibleItemPosition = this.lastVisibleItemPosition

        // If the totalHour item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < this.previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and totalHour item count.
        if (this.loading && totalItemCount > this.previousTotalItemCount) {
            this.loading = false
            this.previousTotalItemCount = totalItemCount
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many totalHour columns there are too
        // if scroll bottom
        if (dy >= 0) {
            if (!this.loading && lastVisibleItemPosition + VISIBLE_THRESHOLD > totalItemCount) {
                this.currentPage++
                onLoadMore.invoke(this.currentPage)
                this.loading = true
            }
        }
    }

    fun reset() {
        this.currentPage = this.startingPageIndex
        this.previousTotalItemCount = 0
        this.loading = true

        layoutManager?.scrollToPosition(0)
    }

    fun resetNoScroll() {
        this.currentPage = this.startingPageIndex
        this.previousTotalItemCount = 0
        this.loading = true
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    companion object {

        private const val VISIBLE_THRESHOLD = 5
    }
}
