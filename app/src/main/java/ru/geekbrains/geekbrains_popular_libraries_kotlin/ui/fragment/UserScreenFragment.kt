package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentUserScreenBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserScreenPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserScreenView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackClickListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter.ReposAdapter

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
        UserScreenPresenter(user).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentUserScreenBinding? = null
    private var adapter: ReposAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserScreenBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun init() {
        vb?.rvRepos?.layoutManager = LinearLayoutManager(requireContext())
        adapter = ReposAdapter(presenter.userReposListPresenter)
        vb?.rvRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backClick()

    override fun addText(login: String) {
        vb?.textLogin?.text = login
    }

}