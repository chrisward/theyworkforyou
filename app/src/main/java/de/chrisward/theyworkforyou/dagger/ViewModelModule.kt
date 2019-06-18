package de.chrisward.theyworkforyou.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import de.chrisward.theyworkforyou.viewmodel.FactoryViewModel
import de.chrisward.theyworkforyou.viewmodel.LordViewModel
import de.chrisward.theyworkforyou.viewmodel.MPViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: FactoryViewModel): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MPViewModel::class)
    internal abstract fun bindMPViewModel(mpViewModel: MPViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LordViewModel::class)
    internal abstract fun bindLordViewModel(lordViewModel: LordViewModel): ViewModel
}