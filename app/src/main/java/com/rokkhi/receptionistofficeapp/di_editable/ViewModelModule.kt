package live.qtec.survey.di_editable


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.di.ViewModelKey
import com.rokkhi.receptionistofficeapp.ui.splash.SplashViewModel
import com.rokkhi.receptionistofficeapp.viewmodel.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}