package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentForksScreenBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentUserScreenBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.api.ApiHolder
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.UserRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.RetrofitGithubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.RepoForksPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserScreenPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.RepoForksView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserScreenView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackClickListener

class RepoForksFragment() : MvpAppCompatFragment(), RepoForksView, BackClickListener {

    companion object {
        private const val FORKS_ARG = "forks"

        fun newInstance(repo: UserRepos) = RepoForksFragment().apply {
            arguments = Bundle().apply {
                putParcelable(FORKS_ARG, repo)
            }
        }
    }

    private val presenter: RepoForksPresenter by moxyPresenter {
        val forks = arguments?.getParcelable<UserRepos>(FORKS_ARG) as UserRepos
        RepoForksPresenter(forks, App.instance.router)
    }

    private var vb: FragmentForksScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentForksScreenBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun addText(forks: String) {
        vb?.repoForks?.text = forks
    }

    override fun backPressed() = presenter.backClick()

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}