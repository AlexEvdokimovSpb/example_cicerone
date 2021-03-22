package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser

interface IDataSource {

    @GET("users")
    //fun getUsers(@Header("Authorization") token: String) : Single<List<GithubUser>>
    fun getUsers(): Single<List<GithubUser>>
//    @GET
//    fun getUserRepos(@Url url: String) : Single

}