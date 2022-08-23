package com.sergnfitness.android.fit.di

import android.app.Application
import com.sergnfitness.cleanarchitect.data.repository.UserRepositoryImpl
import com.sergnfitness.data.api.ApiServer
import com.sergnfitness.data.repository.ApiRepositoryImpl
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModuleApiRepos {

    @Binds
    @Singleton
    abstract fun bindApiRepository(
        apiRepositoryImpl: ApiRepositoryImpl
    ): ApiRepository


}