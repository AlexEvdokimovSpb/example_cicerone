package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.repository

import dagger.Subcomponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.repository.module.RepositoryModule
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.RepoForksPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserScreenPresenter

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(userScreenPresenter: UserScreenPresenter)
    fun inject(repoPresenter: RepoForksPresenter)
}