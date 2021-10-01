package com.example.utils.base

abstract class BaseRepository {

    abstract fun getScheduler(): BaseSchedulerProvider
}