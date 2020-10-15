package  com.rokkhi.receptionistofficeapp.network.module

import com.rokkhi.receptionistofficeapp.helper.SharedPrefHelper
import com.rokkhi.receptionistofficeapp.util.KeyFrame
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class OkHttpModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(loggerInterceptor: HttpLoggingInterceptor, helper: SharedPrefHelper): OkHttpClient {
        val timeOut = 60
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)

        httpClient.addInterceptor(loggerInterceptor)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", helper.getString(KeyFrame.USER_AUTH)) //TODO aws auth token here
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideLoggerInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.e(message)
            }

        })
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.HEADERS }
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return interceptor
    }
}