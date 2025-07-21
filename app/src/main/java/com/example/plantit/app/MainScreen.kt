package com.example.plantit.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.plantit.app.navigation.topLevelRoutes
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.core.presentation.theme.Green200
import com.example.plantit.core.presentation.theme.GreenDark
import com.example.plantit.core.presentation.theme.GreenLight
import com.example.plantit.features.ai_helper.presentation.AIHelper
import com.example.plantit.features.dashboard.presentation.TasksScreen
import com.example.plantit.features.plant_detail.presentation.PlantDetail
import com.example.plantit.features.plant_detail.presentation.PlantDetailScreenRoot
import com.example.plantit.features.user_plant_list.presentation.UserPlantList
import com.example.plantit.features.plant_search.presentation.PlantList
import com.example.plantit.features.plant_search.presentation.PlantSearchScreenRoot
import com.example.plantit.features.user_plant_list.presentation.UserPlantListRoot
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        containerColor = GreenLight,
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                topLevelRoutes.forEach { route ->
                    val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(route.route::class) } == true
                    NavigationBarItem(
                        selected = isSelected,
                        label = {
                            Text(
                                text = route.name,
                                color = if (isSelected) {
                                    GreenDark
                                } else {
                                    Color.Black
                                }
                            )
                        },
                        onClick = {
                            navController.navigate(route.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                        icon = {
                            val icon = if (isSelected) {
                                route.selectedIcon
                            } else {
                                route.unselectedIcon
                            }
                            Icon(
                                modifier = Modifier.size(Dimens.iconSize16),
                                painter = painterResource(id = icon),
                                contentDescription = route.name,
                                tint = if (isSelected) {
                                    GreenDark
                                } else {
                                    Color.Black
                                }
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Green200)
        ) {
            NavHost (
                    navController = navController,
            startDestination = TasksScreen
            ) {
            composable<TasksScreen> {
                TasksScreen()
            }
            composable<PlantList> {
                PlantSearchScreenRoot(
                    onPlantClick = {
                        navController.navigate(PlantDetail(it.id))
                    }
                )
            }
            composable<AIHelper> {
               // AIHelper(navController)
            }
            composable<UserPlantList> {
                UserPlantListRoot()
            }
            composable<PlantDetail> {
                PlantDetailScreenRoot(
                    viewModel = koinViewModel(),
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
        }
    }
}