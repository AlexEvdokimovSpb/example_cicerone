package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.UserRepos
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment.RepoForksFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment.UserScreenFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(githubUser: GithubUser) =
        FragmentScreen { UserScreenFragment.newInstance(githubUser) }

    override fun repo(userRepos: UserRepos) =
        FragmentScreen { RepoForksFragment.newInstance(userRepos) }

}