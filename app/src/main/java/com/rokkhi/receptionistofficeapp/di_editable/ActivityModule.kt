package live.qtec.survey.di_editable

import com.rokkhi.receptionistofficeapp.di.ActivityScope
import com.rokkhi.receptionistofficeapp.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

}