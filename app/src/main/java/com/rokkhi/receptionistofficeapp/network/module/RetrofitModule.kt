package  com.rokkhi.receptionistofficeapp.network.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rokkhi.receptionistofficeapp.base.BaseUrlModule
import com.rokkhi.receptionistofficeapp.di.RokkhiApiUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [
    OkHttpModule::class,
    BaseUrlModule::class
])
class RetrofitModule {

    @Provides
    @Singleton
    @RokkhiApiUrl
    fun provideQtecRetrofit(@RokkhiApiUrl baseUrl: String, okHttpClient: OkHttpClient, factory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(factory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }









}