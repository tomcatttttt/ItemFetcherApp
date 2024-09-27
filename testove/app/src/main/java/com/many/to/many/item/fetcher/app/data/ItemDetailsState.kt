package com.many.to.many.item.fetcher.app.data

data class ItemDetailsState(
    val isLoading: Boolean = false,
    val item: Item? = null,
    val itemDetails: String? = null,
    val error: String? = null
)
