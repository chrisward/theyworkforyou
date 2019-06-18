package de.chrisward.theyworkforyou

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import de.chrisward.theyworkforyou.dagger.DaggerAppComponent
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        this.initDagger()
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}