package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.repository.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.repository.IRepositoryScopeContainer
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.repository.RepositoryScope
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IUserReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.RoomUserReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IUserRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.RetrofitUserRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App

@Module
class RepositoryModule {

    @RepositoryScope
    @Provides
    fun repositoriesCache(database: Database): IUserReposCache =
        RoomUserReposCache(database)

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserReposCache
    ): IUserRepo = RetrofitUserRepo(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    fun repositoryScopeContainer(app: App): IRepositoryScopeContainer = app

}