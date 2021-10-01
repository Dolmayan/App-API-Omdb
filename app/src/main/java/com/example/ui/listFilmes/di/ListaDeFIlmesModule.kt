package com.example.ui.listFilmes.di

import com.example.data.business.FilmsApi
import com.example.data.business.FilmsRepositoryImpl
import com.example.ui.listFilmes.viewModel.ListFilmViewModel
import com.example.utils.APIEnum
import com.example.utils.extension.ApiSessionRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object ListaDeFIlmesModule {

    const val LISTA_DE_FILMES_SCOPE = "LISTA_DE_FILMES_SCOPE"
    const val LISTA_DE_FILMES_SCOPE_ID = "LISTA_DE_FILMES_SCOPE_ID"

    val listaDeFilmesModule = module {
        scope(named(LISTA_DE_FILMES_SCOPE)) {
            viewModel {
                ListFilmViewModel(
                    api = get()
                )
            }

            scoped {
                FilmsRepositoryImpl(
                    api = get(named(APIEnum.AAF_API_SESSION)),
                    scheduler = get()
                )
            }

            scoped<FilmsApi>(named(APIEnum.AAF_API_SESSION)) {
                get<Retrofit>(ApiSessionRequest).newBuilder().build()
                    .create(FilmsApi::class.java)
            }
        }
    }
}