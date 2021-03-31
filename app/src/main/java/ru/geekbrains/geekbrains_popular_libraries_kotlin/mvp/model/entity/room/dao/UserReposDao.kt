package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomUserRepos

@Dao
interface UserReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomUserRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RoomUserRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomUserRepos>)

    @Update
    fun update(user: RoomUserRepos)

    @Update
    fun update(vararg users: RoomUserRepos)

    @Update
    fun update(users: List<RoomUserRepos>)

    @Delete
    fun delete(user: RoomUserRepos)

    @Delete
    fun delete(vararg users: RoomUserRepos)

    @Delete
    fun delete(users: List<RoomUserRepos>)

    @Query("SELECT * FROM RoomUserRepos")
    fun getAll(): List<RoomUserRepos>

    @Query("SELECT * FROM RoomUserRepos WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomUserRepos>

}