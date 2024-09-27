package com.many.to.many.item.fetcher.app.domain

import com.many.to.many.item.fetcher.app.data.ApiService
import com.many.to.many.item.fetcher.app.data.Item

class ItemRepository(private val apiService: ApiService) {

    suspend fun getItems(): Result<List<Item>> {
        return try {
            val response = apiService.getRandomItems()
            if (response.isSuccessful) {
                Result.success(response.body()?.items ?: emptyList())
            } else {
                Result.failure(Exception("Error fetching items: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
