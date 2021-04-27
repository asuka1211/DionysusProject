package com.serma.dionysus.common.ui

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable


inline fun <T> LazyListScope.pagingList(
    item: PagingItems<T>,
    loading: Boolean,
    crossinline loadMore: () -> Unit,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
    crossinline itemLoadingContent: @Composable LazyItemScope.() -> Unit
) {
    var doubleCheck = loading
    this.itemsIndexed(item.items, key) { pos, event ->
        if (pos == item.lastPos && !loading && !doubleCheck) {
            doubleCheck = true
            loadMore()
        }
        itemContent(pos, event)
    }
    if (loading){
        this.item {
            itemLoadingContent()
        }
    }
}

data class PagingItems<T>(val lastPos: Int, val items: List<T>)