package com.example.plantit.app.navigation

import com.example.plantit.R
import com.example.plantit.features.ai_helper.presentation.AIHelper
import com.example.plantit.features.user_plant_list.presentation.UserPlantList
import com.example.plantit.features.plant_search.presentation.PlantList
import com.example.plantit.features.dashboard.presentation.TasksScreen

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val unselectedIcon: Int,
    val selectedIcon: Int,
    val subRoutes: List<T> = emptyList(),
    val showBadge: Boolean = false,
)

val topLevelRoutes = listOf(
    TopLevelRoute(
        name = "Zadania",
        route = TasksScreen,
        selectedIcon = R.drawable.ic_task_list_filled,
        unselectedIcon = R.drawable.ic_task_list
    ),
    TopLevelRoute(
        name = "Szukaj",
        route = PlantList,
        selectedIcon = R.drawable.ic_search_selected,
        unselectedIcon = R.drawable.ic_search
    ),
    TopLevelRoute(
        name = "AI Helper",
        route = AIHelper,
        selectedIcon = R.drawable.ai_filled,
        unselectedIcon = R.drawable.ai_outlined,
    ),
    TopLevelRoute(
        name = "Moje rośliny",
        route = UserPlantList,
        selectedIcon = R.drawable.flower_filled,
        unselectedIcon = R.drawable.flower_outline,
    )
)
