package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentUserScreenBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserScreenPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserScreenView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackClickListener

class UserScreenFragment() : MvpAppCompatFragment(), UserScreenView, BackClickListener {

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserScreenFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private val presenter: UserScreenPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserScreenPresenter(user, App.instance.router)
    }

    private var vb: FragmentUserScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserScreenBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backClick()

    override fun addText(login: String) {
        vb?.textLogin?.text = login
    }

}