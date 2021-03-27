package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ItemRepoBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IReposListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.IUserRepoItemView

class ReposAdapter(val presenter: IReposListPresenter) :
    RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        IUserRepoItemView {
        override fun setName(text: String) {
            vb.repoName.text = text
        }

        override fun setDescription(text: String) {
            vb.repoDescription.text = text
        }

        override var pos = -1
    }
}