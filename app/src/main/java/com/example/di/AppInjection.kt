package com.example.di

import android.app.Application
import com.example.di.AppModule.appModule
import com.example.di.AppModule.daoModule
import com.example.ui.detailsFilm.di.DetailsFilmModule.detailsFilmModule
import com.example.ui.listFavoriteFilmes.di.ListFavoriteFilmModule.listFavoriteModule
import com.example.ui.listFilmes.di.ListaDeFIlmesModule.listaDeFilmesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppInjection: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppInjection)
            androidLogger()
            modules(
                listOf(
                    appModule,
                    listaDeFilmesModule,
                    listFavoriteModule,
                    detailsFilmModule,
                    daoModule
                )
            )
        }
    }
}