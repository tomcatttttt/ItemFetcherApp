package com.many.to.many.item.fetcher.app.di

import com.many.to.many.item.fetcher.app.data.RetrofitInstance
import com.many.to.many.item.fetcher.app.domain.ItemRepository
import com.many.to.many.item.fetcher.app.presentation.ItemViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single { ItemRepository(get()) }

    single { RetrofitInstance.api }

    viewModel { ItemViewModel(get()) }
}
