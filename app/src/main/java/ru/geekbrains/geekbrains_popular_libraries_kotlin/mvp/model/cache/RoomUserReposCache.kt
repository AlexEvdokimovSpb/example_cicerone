package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.UserRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomUserRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database

class RoomUserReposCache(val db: Database) : IUserReposCache {

    override fun putUserRepos(user: GithubUser, userRepos: List<UserRepos>) =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
                ?: throw RuntimeException("No user in DB")
            val roomRepos = userRepos.map { repo ->
                RoomUserRepos(
                    repo.id,
                    repo.name,
                    repo.description,
                    repo.forks,
                    repo.url,
                    roomUser.id
                )
            }
            db.userReposDao.insert(roomRepos)
        }.subscribeOn(Schedulers.io())

    override fun getUserRepos(user: GithubUser) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login)
            ?: throw RuntimeException("No user in DB")
        db.userReposDao.findForUser(roomUser.id).map { roomRepo ->
            UserRepos(
                roomRepo.id,
                roomRepo.name,
                roomRepo.description,
                roomRepo.forks,
                roomRepo.url
            )
        }
    }.subscribeOn(Schedulers.io())
}