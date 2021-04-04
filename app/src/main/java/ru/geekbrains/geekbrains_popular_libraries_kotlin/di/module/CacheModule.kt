package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IUserReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IUsersCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.RoomGithubUsersCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.RoomUserReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun usersCache(db: Database): IUsersCache = RoomGithubUsersCache(db)

    @Singleton
    @Provides
    fun reposCache(db: Database): IUserReposCache = RoomUserReposCache(db)
}