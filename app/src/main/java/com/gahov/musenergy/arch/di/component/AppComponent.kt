package com.gahov.musenergy.arch.di.component

import android.app.Application
import com.gahov.musenergy.MusEnergyApplication
import com.gahov.musenergy.arch.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.MembersInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent : MembersInjector<MusEnergyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}