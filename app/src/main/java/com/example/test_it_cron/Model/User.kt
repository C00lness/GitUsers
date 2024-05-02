package com.example.test_it_cron.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey (autoGenerate = true)
    val uid: Int,
    var avatar_url: String,
    var login: String,
    var email: String?,
    var company: String?,
    var following: Int,
    var followers: Int,
    var created_at: String
)
