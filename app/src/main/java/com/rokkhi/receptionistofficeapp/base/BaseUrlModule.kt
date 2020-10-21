package com.rokkhi.receptionistofficeapp.base

import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseUrlModule {
    @Singleton
    @Provides
    @RokkhiApiUrl
    fun provideBaseUrl():String = "http://13.76.190.212:3000/"
}