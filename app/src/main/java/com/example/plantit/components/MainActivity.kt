@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.example.plantit.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantit.MainViewModel
import com.example.plantit.MainViewModelFactory
import com.example.plantit.model.Plant
import com.example.plantit.model.PlantRequirement
import com.example.plantit.R
import com.example.plantit.UriReader
import com.example.plantit.ui.theme.PlantITTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlantITTheme {
                Scaffold (modifier = Modifier.fillMaxSize()){ innerPadding ->
                    val uriReader = UriReader(applicationContext)
                    val viewModel = viewModel<MainViewModel>(
                        factory = MainViewModelFactory(uriReader)
                    )
                    var selected by remember {
                        mutableStateOf(0)
                    }
                    val bottomNavItems = listOf(
                        BottomNavItem(
                            title = "Zadania",
                            route = TasksScreen,
                            selectedIcon = Icons.Filled.CheckCircle,
                            unselectedIcon = Icons.Outlined.CheckCircle
                        ),
                        BottomNavItem(
                            title = "Szukaj",
                            route = PlantList,
                            selectedIcon = Icons.Filled.Search,
                            unselectedIcon = Icons.Outlined.Search
                        ),
                        BottomNavItem(
                            title = "AI Helper",
                            route = AIHelper,
                            selectedIcon = ImageVector.vectorResource(id = R.drawable.ai_filled),
                            unselectedIcon = ImageVector.vectorResource(id = R.drawable.ai_outlined),
                        ),
                        BottomNavItem(
                            title = "Moje rośliny",
                            route = MyPlants,
                            selectedIcon = ImageVector.vectorResource(id = R.drawable.flower_filled),
                            unselectedIcon = ImageVector.vectorResource(id = R.drawable.flower_outline),
                        )
                    )


                    NavigationSuiteScaffold(
                        navigationSuiteItems = {
                            bottomNavItems.forEachIndexed { index, navItem ->
                                item(
                                    selected = index == selected,
                                    onClick = {
                                        selected = index
                                    },
                                    icon = {
                                        Box {
                                            Icon(
                                                imageVector =
                                                if (index == selected)
                                                    navItem.selectedIcon
                                                else
                                                    navItem.unselectedIcon,
                                                contentDescription = navItem.title
                                            )
                                        }
                                    },
                                    label = {
                                        Text(text = navItem.title)
                                    }
                                )

                            }
                        }
                    ) {

                        when (selected) {
                            0 -> TasksScreen(viewModel = viewModel)
                            1 -> PlantList(viewModel = viewModel)
                            2 -> AIHelper(viewModel = viewModel)
                            3 -> MyPlants(viewModel = viewModel)
                        }
                        /*Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = PlantList
                            ) {
                                composable<PlantList> {
                                    PlantList()
                                }
                                composable<AIHelper> {
                                    AIHelper()
                                }
                            }
                        }*/


                    }

                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewPlantList() {
    val samplePlants = listOf(
        Plant(
            id = 1,
            name = "Monstera",
            photo = "https://media.gettyimages.com/id/1202757463/photo/monstera-deliciosa-houseplant-in-bright-sunlight.jpg?s=2048x2048&w=gi&k=20&c=euSw_zGHU2bkrcsUfow9wfV0ZO6eQfeBkfmZsHoj3cg=",
            plantRequirement = PlantRequirement(
                watering = "Rzadko",
                fertilization = "Co dwa tygodnie",
                rotating = "Nie wymaga",
                soil = "Ziemia uniwersalna"
            )
        ),
        Plant(
            id = 2,
            name = "Fikus",
            photo = "https://www.jungleboogie.pl/wp-content/uploads/2020/05/ficus-belize_14x35-0-scaled-756x1008.jpg",
            plantRequirement = PlantRequirement(
                watering = "Umiarkowanie",
                fertilization = "Raz w miesiącu",
                rotating = "Wymaga",
                soil = "Ziemia uniwersalna"
            )
        )
    )

}