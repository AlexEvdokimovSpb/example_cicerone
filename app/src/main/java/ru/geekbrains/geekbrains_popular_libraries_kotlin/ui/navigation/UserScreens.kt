package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation.UScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment.UserScreenFragment

class UserScreens : UScreens {
    override fun user(login: GithubUser) = FragmentScreen { UserScreenFragment.newInstance(login) }
}