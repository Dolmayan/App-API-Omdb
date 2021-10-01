package com.example.ui.detailsFilm.di

import com.example.data.business.FilmsApi
import com.example.data.business.FilmsRepositoryImpl
import com.example.ui.detailsFilm.viewModel.DetailsFilmViewModel
import com.example.utils.APIEnum
import com.example.utils.extension.ApiSessionRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object DetailsFilmModule {

    const val DETAILS_FILM_SCOPE = "DETAILS_FILM_SCOPE"
    const val DETAILS_FILM_SCOPE_ID = "DETAILS_FILM_SCOPE_ID"

    val detailsFilmModule = module {
        scope(named(DETAILS_FILM_SCOPE)) {
            viewModel {
                DetailsFilmViewModel(
                   // database = get(),
                    api = get()
                )
            }

            scoped {
                FilmsRepositoryImpl(
                    api = get(named(APIEnum.AAF_API_SESSION)),
                    scheduler = get()
                )
            }
//
//            scoped { DatabaseDataSource(
//                filmFavoriteDAO = get(),
//                scheduler = get()
//            ) }

            scoped<FilmsApi>(named(APIEnum.AAF_API_SESSION)) {
                get<Retrofit>(ApiSessionRequest).newBuilder().build()
                    .create(FilmsApi::class.java)
            }
        }
    }
}