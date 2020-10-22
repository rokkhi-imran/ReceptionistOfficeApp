package com.rokkhi.receptionistofficeapp.di_editable


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rokkhi.receptionistofficeapp.di.ViewModelKey
import com.rokkhi.receptionistofficeapp.ui.attendance_in.AttendanceInViewModel
import com.rokkhi.receptionistofficeapp.ui.attendance_out.AttendanceOutViewModel
import com.rokkhi.receptionistofficeapp.ui.main.MainViewModel
import com.rokkhi.receptionistofficeapp.ui.parcel_delivery.ParcelDeliveryViewModel
import com.rokkhi.receptionistofficeapp.ui.parcel_received.ParcelReceivedViewModel
import com.rokkhi.receptionistofficeapp.ui.splash.SplashViewModel
import com.rokkhi.receptionistofficeapp.ui.visitor_in.VisitorInViewModel
import com.rokkhi.receptionistofficeapp.ui.visitor_out.VisitorListViewModel
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
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AttendanceInViewModel::class)
    abstract fun bindAttendanceInViewModel(viewModel: AttendanceInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AttendanceOutViewModel::class)
    abstract fun bindAttendanceOutViewModel(viewModel: AttendanceOutViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VisitorInViewModel::class)
    abstract fun bindVisitorInViewModel(viewModel: VisitorInViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(VisitorListViewModel::class)
    abstract fun bindVisitorListViewModel(viewModel: VisitorListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ParcelDeliveryViewModel::class)
    abstract fun bindParcelDeliveryViewModel(viewModel: ParcelDeliveryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ParcelReceivedViewModel::class)
    abstract fun bindParcelReceivedViewModel(viewModel: ParcelReceivedViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}