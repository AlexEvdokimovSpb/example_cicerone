package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserScreenView

class UserScreenPresenter(val login: GithubUser, val router: Router) :
    MvpPresenter<UserScreenView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.addText(login.toString())
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}