package com.cockroach.di

import com.cockroach.network.NetClient
import com.cockroach.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
    pokedexClient: NetClient,
    coroutineDispatcher: CoroutineDispatcher
  ): MainRepository {
    return MainRepository(pokedexClient,coroutineDispatcher)
  }
}
