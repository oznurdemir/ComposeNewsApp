package com.example.composenewsapp.di

import android.app.Application
import com.example.composenewsapp.data.manager.LocalUserManagerImpl
import com.example.composenewsapp.domain.manager.LocalUserManager
import com.example.composenewsapp.domain.usecases.AppEntryUseCases
import com.example.composenewsapp.domain.usecases.ReadAppEntry
import com.example.composenewsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}