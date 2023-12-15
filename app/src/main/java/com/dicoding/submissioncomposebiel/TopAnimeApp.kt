@file:OptIn(ExperimentalMaterial3Api::class)

package com.dicoding.submissioncomposebiel

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.submissioncomposebiel.data.AnimeRepository
import com.dicoding.submissioncomposebiel.navigation.NavItem
import com.dicoding.submissioncomposebiel.navigation.Screen
import com.dicoding.submissioncomposebiel.ui.screen.ViewModelFactory
import com.dicoding.submissioncomposebiel.ui.screen.detail.DetailScreen
import com.dicoding.submissioncomposebiel.ui.screen.detail.DetailViewModel
import com.dicoding.submissioncomposebiel.ui.screen.home.HomeScreen
import com.dicoding.submissioncomposebiel.ui.screen.home.HomeViewModel
import com.dicoding.submissioncomposebiel.ui.screen.profile.ProfileScreen
import com.dicoding.submissioncomposebiel.ui.theme.SubmissionComposeBielTheme

@Composable
fun TopAnimeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val animeRepository = remember { AnimeRepository() } // Create an instance of AnimeRepository

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailAnime.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { id -> navController.navigate(Screen.DetailAnime.createRoute(id)) })
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = "home/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) { backStackEntry ->
                val animeId = backStackEntry.arguments?.getLong("id") ?: return@composable
                val viewModel: DetailViewModel = viewModel(
                    factory = ViewModelFactory(animeRepository)
                )
                DetailScreen(
                    viewModel = viewModel,
                    id = animeId,
                    navigateBack = { navController.navigateUp() }
                )
            }
        }

    }
}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navigationItems = listOf(
            NavItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = false,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SubmissionComposeBielTheme {
        TopAnimeApp()
    }
}