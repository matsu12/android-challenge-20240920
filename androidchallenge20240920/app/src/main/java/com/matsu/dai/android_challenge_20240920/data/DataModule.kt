package com.matsu.dai.android_challenge_20240920.data.repo

import android.content.Context
import com.matsu.dai.android_challenge_20240920.data.local.LibraryLicenseList
import com.matsu.dai.android_challenge_20240920.data.repo.local.LocalData
import com.matsu.dai.android_challenge_20240920.data.repo.remote.AuthInterceptor
import com.matsu.dai.android_challenge_20240920.data.repo.remote.ChatWorkApis
import com.matsu.dai.android_challenge_20240920.data.repo.remote.ChatWorkServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideChatWorkRepo(remote: ChatWorkApis, localData: LocalData, libraryLicenseList: LibraryLicenseList): ChatWorkRepo {
        return ChatWorkRepo(remote, localData, libraryLicenseList)
    }

    @Provides
    fun provideAuthInterceptor(localData: LocalData): AuthInterceptor {
        return AuthInterceptor(localData)
    }

    @Provides
    fun provideLocalData(context: Context): LocalData {
        return LocalData(context)
    }

    @Provides
    fun provideCharWorkService(interceptor: AuthInterceptor): ChatWorkApis {
        return ChatWorkServiceFactory().createChatWorkService(interceptor)
    }

    @Provides
    fun provideLibsList(context: Context): LibraryLicenseList {
        return LibraryLicenseList(context)
    }
}