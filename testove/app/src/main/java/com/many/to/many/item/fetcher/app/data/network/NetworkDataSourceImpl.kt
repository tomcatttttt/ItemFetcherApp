package com.many.to.many.item.fetcher.app.data.network

class NetworkDataSourceImpl(private val apiService: ApiService) : NetworkDataSource {
    override suspend fun fetchItems() = apiService.getRandomItems()
    override suspend fun fetchItemDetails(itemId: String) = apiService.getItemDetails(itemId)
}