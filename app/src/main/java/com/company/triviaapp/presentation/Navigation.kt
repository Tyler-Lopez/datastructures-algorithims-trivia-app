package com.company.triviaapp.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import com.company.triviaapp.presentation.select_chapter.ChapterListScreen
import com.company.triviaapp.presentation.select_course.CourseListScreen
import com.company.triviaapp.presentation.theme.TriviaAppTheme
import com.company.triviaapp.presentation.ui.BottomBar
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(navController: NavHostController) {
    // TEMPORARY while figure out theming, this should be saved and will reset everytime for now
    val isDark = remember { mutableStateOf(value = true) }

    TriviaAppTheme(isDark.value) {
        // NavHost requires a navController and the route of the starting destination of your graph
        AnimatedNavHost(navController = navController, startDestination = Screen.MainScreen.route) {
            composable(
                route = Screen.MainScreen.route,
                exitTransition = null,
                popEnterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { -1200 },
                        animationSpec = tween(400)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                },
                enterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { -1200 },
                        animationSpec = tween(400)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                }
            ) {
                Box(modifier = Modifier.padding(bottom = 50.dp)) {
                    CourseListScreen(navController = navController) // Updated for MVVM
                }
            }
            composable(route = Screen.ChapterSelect.route + "/{courseId}",
                exitTransition = null,
                popExitTransition = null,
                popEnterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { -1200 },
                        animationSpec = tween(400, 100)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                },
                enterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { 1200 },
                        animationSpec = tween(400, 100)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                }) {
                Box(modifier = Modifier.padding(bottom = 50.dp)) {
                    ChapterListScreen(
                        navController = navController // MVVM Update
                    )
                }
            }
            composable(route = Screen.FlashCard.route + "/{chapterId}",
                exitTransition = null,
                popExitTransition = null,
                popEnterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { -1200 },
                        animationSpec = tween(400)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                },
                enterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { 1200 },
                        animationSpec = tween(400)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                }) {
                Box(modifier = Modifier.padding(bottom = 50.dp)) {
                    FlashcardView(
                        navController = navController
                    )
                }
            }
            composable(
                route = Screen.Settings.route,
                exitTransition = null,
                popEnterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { -1200 },
                        animationSpec = tween(400)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                },
                enterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { -1200 },
                        animationSpec = tween(400)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                }
            ) {
                Box(modifier = Modifier.padding(bottom = 50.dp)) {
                    SettingsScreen(navController = navController, onToggleTheme = {
                        // This is a call-back called when the toggle dark mode button is clicked
                        isDark.value = !isDark.value
                    })
                }
            }
            composable(
                route = Screen.Search.route,
                exitTransition = null,
                popEnterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { -1200 },
                        animationSpec = tween(400)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                },
                enterTransition = { _, _ ->
                    slideInHorizontally(
                        initialOffsetX = { -1200 },
                        animationSpec = tween(400)
                    ) + fadeIn(initialAlpha = 0.0f, animationSpec = tween(400))
                }
            ) {
                Box(modifier = Modifier.padding(bottom = 50.dp)) {
                    SearchScreen(navController = navController)
                }
            }
        }
    
    Column(modifier = Modifier.fillMaxSize(), Arrangement.Bottom) {
        BottomBar(navController = navController)
    }
}
}