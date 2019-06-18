package de.chrisward.theyworkforyou.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.chrisward.theyworkforyou.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}