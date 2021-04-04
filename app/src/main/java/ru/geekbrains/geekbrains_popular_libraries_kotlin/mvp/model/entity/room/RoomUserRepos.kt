package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomUserRepos(
    @PrimaryKey val id: String,
    val name: String?,
    val description: String?,
    val forks: Int?,
    val url: String?,
    var userId: String
)
