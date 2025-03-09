package com.mobile.e2m.playmusic.di

import com.mobile.e2m.playmusic.data.repository.SongsRepositoryImpl
import com.mobile.e2m.playmusic.domain.repository.SongsRepository
import com.mobile.e2m.playmusic.domain.usecase.GetSongsUseCase
import com.mobile.e2m.playmusic.domain.usecase.GetSongsUseCaseImpl
import com.mobile.e2m.playmusic.presentation.playmusic.PlaymusicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val playmusicModule = module {
    viewModel { PlaymusicViewModel(get()) }

    single { GetSongsUseCaseImpl(get()) } bind GetSongsUseCase::class

    single { SongsRepositoryImpl(get()) } bind SongsRepository::class
}
