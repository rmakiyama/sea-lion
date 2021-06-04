package com.rmakiyama.sealion.data.di

import com.rmakiyama.sealion.data.InMemoryTasksRepository
import com.rmakiyama.sealion.domain.repository.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TasksRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideTasksRepository(bind: InMemoryTasksRepository): TasksRepository
}
