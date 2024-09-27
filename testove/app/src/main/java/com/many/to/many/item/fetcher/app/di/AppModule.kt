package com.many.to.many.item.fetcher.app.di

import com.many.to.many.item.fetcher.app.data.RetrofitInstance
import com.many.to.many.item.fetcher.app.domain.ItemRepository
import org.koin.dsl.module


val appModule = module {
    single { ItemRepository(get()) }

    single { RetrofitInstance.api }
}
