package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache.IUserReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus

class RetrofitUserRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IUserReposCache
) : IUserRepo {
    override fun getUserRepos(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUserRepos(url)
                        .flatMap { userRepos ->
                            cache.putUserRepos(user, userRepos)
                                .andThen(Single.just(userRepos))
                        }
                } ?: Single.error(RuntimeException("User has no repos url"))
            } else {
                cache.getUserRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}
