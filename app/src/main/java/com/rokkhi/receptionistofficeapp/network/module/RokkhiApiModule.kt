package com.rokkhi.receptionistofficeapp.network.module

import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import dagger.Module
import dagger.Provides
import live.qtec.survey.network.RokkhiApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        RetrofitModule::class
    ]
)
open class RokkhiApiModule {

    @Singleton
    @Provides
    @RokkhiApiUrl
    fun provideRokkhiApi(@RokkhiApiUrl retrofit: Retrofit): RokkhiApi {
        return retrofit.create(RokkhiApi::class.java)
    }

}