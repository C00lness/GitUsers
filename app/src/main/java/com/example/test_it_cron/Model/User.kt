package com.example.test_it_cron.Model

data class User(

    var avatar_url: String,
    var login: String,
    var email: String,
    var company: String,
    var following: Int,
    var followers: Int,
    var created_at: String
)
