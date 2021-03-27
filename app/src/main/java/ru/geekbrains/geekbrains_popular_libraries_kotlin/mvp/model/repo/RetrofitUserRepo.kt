package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser

class RetrofitUserRepo(val api: IDataSource) : IUserRepo {
    override fun getUserRepos(user: GithubUser) = user.reposUrl?.let { url ->
        api.getUserRepos(url).subscribeOn(Schedulers.io())
    } ?: Single.error(RuntimeException("No repo url"))
}
