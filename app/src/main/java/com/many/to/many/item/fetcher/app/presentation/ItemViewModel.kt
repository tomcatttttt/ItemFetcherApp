package com.many.to.many.item.fetcher.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.many.to.many.item.fetcher.app.domain.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    private val _state = MutableStateFlow(ItemState())
    val state: StateFlow<ItemState> = _state

    init {
        fetchItems()
    }

    fun fetchItems() {
        viewModelScope.launch {
            _state.value = ItemState(isLoading = true)
            val result = repository.getItems()
            result.fold(
                onSuccess = { itemResponse ->
                    _state.value = ItemState(items = itemResponse.items, category = itemResponse.title)
                },
                onFailure = { error ->
                    _state.value = ItemState(error = error.message)
                }
            )
        }
    }
}
