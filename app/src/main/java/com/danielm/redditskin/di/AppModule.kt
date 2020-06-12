package com.danielm.redditskin.di

import com.danielm.redditskin.mvvm.IRepository
import com.danielm.redditskin.mvvm.PostsViewModel
import com.danielm.redditskin.mvvm.Repository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single <IRepository> { Repository() }

    viewModel { PostsViewModel(get()) }
}