package com.example.di

import com.example.data.business.FilmsRepositoryImpl
import com.example.data.database.AppDatabase
import com.example.data.database.repository.DatabaseDataSource
import com.example.ui.listFilmes.viewModel.ListFilmViewModel
import com.example.utils.base.SchedulerProvider
import com.example.utils.extension.ApiSessionRequest
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    val appModule = module {

        viewModel {
            ListFilmViewModel(api = get())
         //   DetailsFilmViewModel(database = get(), api = get())
        }

        factory(ApiSessionRequest) {
            createRetrofit()
        }

        factory {
            FilmsRepositoryImpl(scheduler = get(), api = get())
        }

        factory {
            DatabaseDataSource(scheduler = get(), filmFavoriteDAO = get())
        }

        single {
            SchedulerProvider()
        }

    }

    val daoModule = module {
        single { AppDatabase.getInstance(androidContext()).filmFavoriteDAO }
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}