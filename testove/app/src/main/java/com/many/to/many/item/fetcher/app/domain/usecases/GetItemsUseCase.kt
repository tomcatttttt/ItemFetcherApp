package com.many.to.many.item.fetcher.app.domain.usecases

import com.many.to.many.item.fetcher.app.data.ItemResponse
import com.many.to.many.item.fetcher.app.domain.ItemRepository

class GetItemsUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(): Result<ItemResponse> {
        return repository.getItems()
    }
}