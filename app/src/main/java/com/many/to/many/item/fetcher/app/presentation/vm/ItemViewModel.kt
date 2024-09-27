package com.many.to.many.item.fetcher.app.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.many.to.many.item.fetcher.app.data.ItemState
import com.many.to.many.item.fetcher.app.domain.usecases.GetItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ItemState())
    val state: StateFlow<ItemState> = _state

    init {
        fetchItems()
    }

    fun fetchItems() {
        viewModelScope.launch {
            val result = getItemsUseCase()
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


