package de.chrisward.theyworkforyou.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.chrisward.theyworkforyou.view.LordListFragment
import de.chrisward.theyworkforyou.view.MPListFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMPListFragment(): MPListFragment

    @ContributesAndroidInjector
    abstract fun contributeLordListFragment(): LordListFragment
}