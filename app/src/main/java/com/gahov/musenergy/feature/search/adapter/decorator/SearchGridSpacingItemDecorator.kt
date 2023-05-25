package com.gahov.musenergy.feature.search.adapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gahov.musenergy.feature.search.adapter.SearchListViewType

class SearchGridSpacingItemDecorator(
    private val spanCount: Int,
    private val screenSpacing: Int,
    private val itemSpacing: Int,
    private val includeEdge: Boolean,
    private val adapter: RecyclerView.Adapter<*>
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (isCategoryItem(position)) {
            val column = getColumn(position)
            if (includeEdge) {
                outRect.left = countSpacingLeft(column)
                outRect.right = countSpacingRight(column)
                outRect.bottom = itemSpacing
                if (position <= spanCount) {
                    outRect.top = itemSpacing
                }
            } else {
                outRect.left = column * itemSpacing / spanCount
                outRect.right =
                    itemSpacing - (column + STATIC_VIEW_HOLDERS_UNTIL_MEDIA) * itemSpacing / spanCount
                outRect.top = itemSpacing
            }
        }
    }

    private fun countSpacingLeft(column: Int): Int {
        return if (column == 0) {
            screenSpacing
        } else {
            itemSpacing - column * itemSpacing / spanCount
        }
    }

    private fun countSpacingRight(column: Int): Int {
        return if (column == spanCount - STATIC_VIEW_HOLDERS_UNTIL_MEDIA) {
            screenSpacing
        } else {
            (column + STATIC_VIEW_HOLDERS_UNTIL_MEDIA) * itemSpacing / spanCount
        }
    }

    private fun getColumn(position: Int): Int {
        return (position - STATIC_VIEW_HOLDERS_UNTIL_MEDIA) % spanCount
    }

    private fun isCategoryItem(position: Int): Boolean {
        return if (position != -1) {
            val viewType = adapter.getItemViewType(position)
            SearchListViewType.values()[viewType] == SearchListViewType.SEARCH_CATEGORY_SECTION
        } else false
    }

    companion object {
        const val STATIC_VIEW_HOLDERS_UNTIL_MEDIA = 1
    }
}