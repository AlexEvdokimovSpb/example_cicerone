package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepos(
    @Expose val name: String?,
    @Expose val description: String?,
    @Expose val forks: Int?,
    @Expose val url: String?,
) : Parcelable