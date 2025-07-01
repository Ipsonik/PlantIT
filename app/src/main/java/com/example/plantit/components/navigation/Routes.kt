package com.example.plantit.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Search
import com.example.plantit.R
import com.example.plantit.components.AIHelper
import com.example.plantit.components.MyPlants
import com.example.plantit.components.PlantList
import com.example.plantit.components.TasksScreen

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
        route = MyPlants,
        selectedIcon = R.drawable.flower_filled,
        unselectedIcon = R.drawable.flower_outline,
    )
)
