package com.example.plantit.ui.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.plantit.MainViewModel
import com.example.plantit.components.AIHelper
import com.example.plantit.components.MyPlants
import com.example.plantit.components.PlantList
import com.example.plantit.components.TasksScreen
import com.example.plantit.components.navigation.topLevelRoutes
import com.example.plantit.ui.Dimens
import com.example.plantit.ui.theme.Green200
import com.example.plantit.ui.theme.GreenDark
import com.example.plantit.ui.theme.GreenLight

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val context = LocalContext.current

    Scaffold(
        containerColor = GreenLight,
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
                topLevelRoutes.forEach { route ->
                    val isSelected = currentDestination?.route == route.route
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

        val viewModel = viewModel<MainViewModel>()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Green200)
        ) {
            NavHost(
                navController = navController,
                startDestination = TasksScreen
            ) {
                composable<TasksScreen> {
                    TasksScreen(viewModel)
                }
                composable<PlantList> {
                    PlantList(viewModel = viewModel)
                }
                composable<AIHelper> {
                    AIHelper(viewModel, navController)
                }
                composable<MyPlants> {
                    MyPlants(viewModel)
                }
            }
        }
    }
}