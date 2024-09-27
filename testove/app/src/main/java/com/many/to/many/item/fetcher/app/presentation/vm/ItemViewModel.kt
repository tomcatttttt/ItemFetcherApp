package com.many.to.many.item.fetcher.app.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.many.to.many.item.fetcher.app.data.ItemState
import com.many.to.many.item.fetcher.app.domain.usecases.GetItemsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ItemState(isLoading = true))
    val state: StateFlow<ItemState> = _state

    init {
        fetchItems()
    }

    private suspend fun retryFetchItems() {
        while (true) {
            val result = getItemsUseCase()
            result.fold(
                onSuccess = { itemResponse ->
                    val hasMissingImages = itemResponse.items.any { it.image.isNullOrEmpty() }
                    if (!hasMissingImages) {
                        _state.value = ItemState(items = itemResponse.items, category = itemResponse.title)
                        return
                    } else {
                        delay(1)
                    }
                },
                onFailure = { error ->
                    delay(1000)
                }
            )
        }
    }

    fun fetchItems() {
        viewModelScope.launch {
            _state.value = ItemState(isLoading = true)
            retryFetchItems()
        }
    }
}
