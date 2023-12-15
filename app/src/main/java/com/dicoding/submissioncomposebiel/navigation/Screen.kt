package com.dicoding.submissioncomposebiel.navigation

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Profile: Screen("profile")
    object DetailAnime: Screen("home/{id}"){
        fun createRoute(id: Long) = "home/$id"
    }
}
