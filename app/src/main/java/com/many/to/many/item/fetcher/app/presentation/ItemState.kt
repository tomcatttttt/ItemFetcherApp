package com.many.to.many.item.fetcher.app.presentation

import com.many.to.many.item.fetcher.app.data.Item

data class ItemState(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: String? = null
)
