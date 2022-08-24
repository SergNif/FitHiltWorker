package com.sergnfitness.android.fit.di


import com.sergnfitness.cleanarchitect.data.repository.UserRepositoryImpl
import com.sergnfitness.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModuleUserRepos {

//    @Binds
//    abstract fun bindUserRepository(
//        userRepositoryImpl: UserRepositoryImpl
//    ): UserRepository
//

}