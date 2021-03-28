package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.UserRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomUserRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus

class RetrofitUserRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IUserRepo {
    override fun getUserRepos(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUserRepos(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = db.userDao.findByLogin(user.login)
                                    ?: throw RuntimeException("No user in DB")
                                val roomRepos = repositories.map { repo ->
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
                                repositories
                            }
                        }
                } ?: Single.error(RuntimeException("User has no repos url"))
            } else {
                Single.fromCallable {
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
                }
            }
        }.subscribeOn(Schedulers.io())
}