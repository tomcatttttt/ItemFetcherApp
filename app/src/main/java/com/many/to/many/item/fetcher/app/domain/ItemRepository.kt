package com.many.to.many.item.fetcher.app.domain

import com.many.to.many.item.fetcher.app.data.network.ApiService
import com.many.to.many.item.fetcher.app.data.ItemDetailsResponse
import com.many.to.many.item.fetcher.app.data.ItemResponse
import com.many.to.many.item.fetcher.app.data.network.NetworkDataSource

class ItemRepository(private val networkDataSource: NetworkDataSource) {

    suspend fun getItems(): Result<ItemResponse> {
        return try {
            val response = networkDataSource.fetchItems()
            if (response.isSuccessful) {
                Result.success(response.body() ?: ItemResponse(title = "", items = emptyList()))
            } else {
                Result.failure(Exception("Error fetching items: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getItemDetails(itemId: String): Result<ItemDetailsResponse> {
        return try {
            val response = networkDataSource.fetchItemDetails(itemId)
            if (response.isSuccessful) {
                Result.success(response.body() ?: ItemDetailsResponse(id = itemId, text = "No details available"))
            } else {
                Result.failure(Exception("Error fetching details: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

