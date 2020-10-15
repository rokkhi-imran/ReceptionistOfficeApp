package com.rokkhi.receptionistofficeapp.di

import android.app.Application
import com.rokkhi.receptionistofficeapp.base.RokkhiApplication
import com.rokkhi.receptionistofficeapp.network.module.RokkhiApiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import live.qtec.survey.di_editable.ActivityModule
import live.qtec.survey.di_editable.FragmentModule
import live.qtec.survey.di_editable.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        FragmentModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        RokkhiApiModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(application: RokkhiApplication)
}