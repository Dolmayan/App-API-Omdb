package com.example.ui.listFavoriteFilmes.di

import com.example.ui.listFavoriteFilmes.viewModel.ListFavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object ListFavoriteFilmModule {

    const val LIST_FAVORITE_SCOPE = "LIST_FAVORITE_SCOPE"
    const val LIST_FAVORITE_SCOPE_ID = "LIST_FAVORITE_SCOPE_ID"

    val listFavoriteModule = module {
        scope(named(LIST_FAVORITE_SCOPE)) {
            viewModel {
                ListFavoriteViewModel(
                    repository = get()
                )
            }
        }
    }
}