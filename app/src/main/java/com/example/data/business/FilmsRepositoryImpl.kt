package com.example.data.business

import com.example.data.response.FilmDTO
import com.example.data.response.FilmesListResponse
import com.example.utils.base.BaseRepository
import com.example.utils.base.BaseSchedulerProvider
import com.example.utils.base.SchedulerProvider
import io.reactivex.Single

class FilmsRepositoryImpl(private val api: FilmsApi, private val scheduler: SchedulerProvider) :
    BaseRepository(), FilmsRepository {

    override fun getFilmes(filmeName: String?, page: Int): Single<FilmDTO> {
        return api.getFilmes(filmeName, page).subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }

    override fun getFilmForId(id: String?): Single<FilmesListResponse> {
        return api.getFilmForId(id).subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }

    override fun getScheduler(): BaseSchedulerProvider {
        return scheduler
    }
}