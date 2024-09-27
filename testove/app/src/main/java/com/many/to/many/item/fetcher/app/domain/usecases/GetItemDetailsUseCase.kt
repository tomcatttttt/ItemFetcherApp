package com.many.to.many.item.fetcher.app.domain.usecases

import com.many.to.many.item.fetcher.app.data.ItemDetailsResponse
import com.many.to.many.item.fetcher.app.domain.ItemRepository

class GetItemDetailsUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(itemId: String): Result<ItemDetailsResponse> {
        return repository.getItemDetails(itemId)
    }
}