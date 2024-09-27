package com.many.to.many.item.fetcher.app.di

import com.many.to.many.item.fetcher.app.data.network.NetworkDataSource
import com.many.to.many.item.fetcher.app.data.network.NetworkDataSourceImpl
import com.many.to.many.item.fetcher.app.data.network.RetrofitInstance
import com.many.to.many.item.fetcher.app.domain.ItemRepository
import com.many.to.many.item.fetcher.app.domain.usecases.GetItemDetailsUseCase
import com.many.to.many.item.fetcher.app.domain.usecases.GetItemsUseCase
import com.many.to.many.item.fetcher.app.presentation.vm.ItemDetailsViewModel
import com.many.to.many.item.fetcher.app.presentation.vm.ItemViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single { RetrofitInstance.api }
    single<NetworkDataSource> { NetworkDataSourceImpl(get()) }
    single { ItemRepository(get()) }
    single { GetItemsUseCase(get()) }
    single { GetItemDetailsUseCase(get()) }
    viewModel { ItemViewModel(get()) }
    viewModel { ItemDetailsViewModel(get()) }
}

