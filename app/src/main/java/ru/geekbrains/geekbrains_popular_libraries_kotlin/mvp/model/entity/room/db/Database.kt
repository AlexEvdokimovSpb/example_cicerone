package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomUserRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.UserDao
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao.UserReposDao

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomUserRepos::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val userReposDao: UserReposDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw IllegalStateException("Database has not been created")
        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
            }
        }
    }
}