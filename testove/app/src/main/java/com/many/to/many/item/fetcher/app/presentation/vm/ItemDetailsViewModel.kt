package com.many.to.many.item.fetcher.app.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.many.to.many.item.fetcher.app.data.ItemDetailsState
import com.many.to.many.item.fetcher.app.domain.usecases.GetItemDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class ItemDetailsViewModel(
    private val getItemDetailsUseCase: GetItemDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ItemDetailsState())
    val state: StateFlow<ItemDetailsState> = _state

    fun fetchItemDetails(itemId: String) {
        viewModelScope.launch {
            _state.value = ItemDetailsState(isLoading = true)

            val result = getItemDetailsUseCase(itemId)
            result.fold(
                onSuccess = { details ->
                    _state.value = ItemDetailsState(itemDetails = details.text)
                },
                onFailure = { error ->
                    _state.value = ItemDetailsState(error = error.message)
                }
            )
        }
    }
}

