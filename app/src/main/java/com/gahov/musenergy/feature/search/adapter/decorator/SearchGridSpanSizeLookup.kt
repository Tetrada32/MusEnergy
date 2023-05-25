package com.gahov.musenergy.feature.search.adapter.decorator

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gahov.musenergy.feature.search.adapter.SearchListViewType

class SearchGridSpanSizeLookup(
    private val adapter: RecyclerView.Adapter<*>
) : GridLayoutManager.SpanSizeLookup() {

    override fun getSpanSize(position: Int): Int {
        val viewType = adapter.getItemViewType(position)
        return when (SearchListViewType.values()[viewType]) {
            SearchListViewType.SEARCH_CATEGORY_SECTION -> SPAN_COUNT_MIN
            else -> SPAN_COUNT_MAX
        }
    }

    companion object {
        private const val SPAN_COUNT_MAX = 2
        private const val SPAN_COUNT_MIN = 1
    }
}