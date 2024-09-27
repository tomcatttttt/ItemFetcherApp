package com.many.to.many.item.fetcher.app.data.network

import com.many.to.many.item.fetcher.app.data.ItemDetailsResponse
import com.many.to.many.item.fetcher.app.data.ItemResponse
import retrofit2.Response

interface NetworkDataSource {
    suspend fun fetchItems(): Response<ItemResponse>
    suspend fun fetchItemDetails(itemId: String): Response<ItemDetailsResponse>
}
