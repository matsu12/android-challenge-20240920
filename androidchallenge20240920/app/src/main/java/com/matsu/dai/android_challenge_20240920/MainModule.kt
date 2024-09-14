package com.matsu.dai.android_challenge_20240920

import com.matsu.dai.android_challenge_20240920.data.repo.ChatWorkRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    fun provideViewModel(charWorkRepo: ChatWorkRepo): MainViewModel {
        return MainViewModel(charWorkRepo)
    }
}