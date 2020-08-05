package com.zenith.stayfit.di

import com.zenith.stayfit.ui.login.network.AuthenticationService
import com.zenith.stayfit.ui.login.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideNoteRepository(gameApi: AuthenticationService): SignUpRepository {
        return SignUpRepository(gameApi)
    }
}