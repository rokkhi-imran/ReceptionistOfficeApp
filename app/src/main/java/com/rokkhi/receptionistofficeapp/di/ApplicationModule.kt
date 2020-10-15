package com.rokkhi.receptionistofficeapp.di

import android.app.Application
import com.rokkhi.receptionistofficeapp.helper.SharedPrefHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideSharedPrefHelper(application:Application) = SharedPrefHelper(application)

}