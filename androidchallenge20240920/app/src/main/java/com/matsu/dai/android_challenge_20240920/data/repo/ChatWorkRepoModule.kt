package com.matsu.dai.android_challenge_20240920.data.repo

import com.matsu.dai.android_challenge_20240920.data.repo.remote.AuthInterceptor
import com.matsu.dai.android_challenge_20240920.data.repo.remote.ChatWorkApis
import com.matsu.dai.android_challenge_20240920.data.repo.remote.ChatWorkServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ChatWorkRepoModule {
    @Provides
    fun provideChatWorkRepo(remote: ChatWorkApis): ChatWorkRepo {
        return ChatWorkRepo(remote)
    }

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    fun provideCharWorkService(interceptor: AuthInterceptor): ChatWorkApis {
        return ChatWorkServiceFactory().createChatWorkService(interceptor)
    }
}