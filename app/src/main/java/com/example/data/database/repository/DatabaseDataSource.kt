package com.example.data.database.repository

import com.example.data.database.dao.FilmFavoriteDAO
import com.example.data.database.entity.FilmFavoriteEntity
import com.example.utils.base.BaseRepository
import com.example.utils.base.BaseSchedulerProvider
import com.example.utils.base.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single

class DatabaseDataSource(
    private val filmFavoriteDAO: FilmFavoriteDAO,
    private val scheduler: SchedulerProvider
) : BaseRepository(), FilmFavoriteDatabase {

    override suspend fun insertFilm(film: FilmFavoriteEntity): Long {
        return filmFavoriteDAO.insert(film)
    }

    override fun deleteFilm(id: String): Completable {
        return filmFavoriteDAO.delete(id).subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }

    override fun getAllFilms(): Single<List<FilmFavoriteEntity>> {
        return filmFavoriteDAO.getAll().subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }

    override fun existFilm(id: String): Single<FilmFavoriteEntity> {
        return filmFavoriteDAO.exist(id).subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }

    override fun getScheduler(): BaseSchedulerProvider {
        return scheduler
    }
}