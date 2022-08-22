package com.sergnfitness.android.fit.di

import android.content.Context
import com.sergnfitness.cleanarchitect.data.repository.UserRepositoryImpl
import com.sergnfitness.cleanarchitect.data.storage.SharedPresImplStorage
import com.sergnfitness.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPresImplStorage(@ApplicationContext context: Context): SharedPresImplStorage {
        return SharedPresImplStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(sharedPresInterfaceStorage: SharedPresImplStorage): UserRepository {
        return UserRepositoryImpl(sharedPresInterfaceStorage = sharedPresInterfaceStorage)
    }


//    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
//        UserRepositoryImpl(sharedPresInterfaceStorage = SharedPresImplStorage(context = context))
//    }

}