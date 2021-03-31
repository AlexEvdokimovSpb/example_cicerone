package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.UserRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.RepoForksView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserScreenView

class RepoForksPresenter(
    val userRepos: UserRepos, val router: Router
) : MvpPresenter<RepoForksView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.addText(userRepos.forks.toString())
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}