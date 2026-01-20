package com.example.learncleanarchitecture.di

import android.content.pm.ApplicationInfo
import com.example.learncleanarchitecture.data.repository.MemeRepositoryImpl
import com.example.learncleanarchitecture.domain.repository.MemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMemeRepository(impl: MemeRepositoryImpl): MemeRepository

}