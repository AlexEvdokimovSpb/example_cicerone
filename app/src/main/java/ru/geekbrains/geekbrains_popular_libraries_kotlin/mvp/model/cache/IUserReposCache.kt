package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.UserRepos

interface IUserReposCache {
    fun putUserRepos(user: GithubUser, userRepos: List<UserRepos>): Completable
    fun getUserRepos(user: GithubUser): Single<List<UserRepos>>
}