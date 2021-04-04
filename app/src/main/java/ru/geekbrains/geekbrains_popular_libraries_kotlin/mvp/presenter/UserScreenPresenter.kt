package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.UserRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.IUserRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IReposListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserScreenView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.IUserRepoItemView
import javax.inject.Inject
import javax.inject.Named

class UserScreenPresenter(
    val user: GithubUser
) : MvpPresenter<UserScreenView>() {

    @Inject
    @field:Named("uiScheduler")
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var usersRepo: IUserRepo

    class UserReposListPresenter : IReposListPresenter {
        val userRepos = mutableListOf<UserRepos>()
        override var itemClickListener: ((IUserRepoItemView) -> Unit)? = null

        override fun bindView(view: IUserRepoItemView) {
            val user = userRepos[view.pos]
            user.name?.let { view.setName(it) }
            user.description?.let { view.setDescription(it) }
        }

        override fun getCount() = userRepos.size
    }

    val userReposListPresenter = UserReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.addText(user.login)
        viewState.init()
        loadData()

        userReposListPresenter.itemClickListener = { view ->
            val forks = userReposListPresenter.userRepos[view.pos]
            router.navigateTo(screens.repo(forks))
        }
    }

    fun loadData() {
        usersRepo.getUserRepos(user)
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                userReposListPresenter.userRepos.clear()
                userReposListPresenter.userRepos.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}