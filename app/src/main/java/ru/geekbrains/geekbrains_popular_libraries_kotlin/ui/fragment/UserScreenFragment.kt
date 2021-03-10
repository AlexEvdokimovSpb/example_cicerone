package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentUserScreenBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserScreenPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserScreenView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackClickListener

class UserScreenFragment() : MvpAppCompatFragment(), UserScreenView, BackClickListener {

    companion object {
        fun newInstance() = UserScreenFragment()
    }

    private val presenter by moxyPresenter {
        UserScreenPresenter("login1", App.instance.router)
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
        vb?.textLogin?.setText(login)
    }

}