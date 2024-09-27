package com.many.to.many.item.fetcher.app.domain

import com.many.to.many.item.fetcher.app.data.ApiService
import com.many.to.many.item.fetcher.app.data.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ItemRepository(private val apiService: ApiService) {

    fun getItems(): Flow<Result<List<Item>>> = flow {
        try {
            val response = apiService.getRandomItems()
            if (response.isSuccessful) {
                emit(Result.success(response.body()?.items ?: emptyList()))
            } else {
                emit(Result.failure(Exception("Error fetching items")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
