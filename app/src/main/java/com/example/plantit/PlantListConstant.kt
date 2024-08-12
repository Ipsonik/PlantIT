package com.example.plantit

import com.example.plantit.model.Plant
import com.example.plantit.model.PlantRequirement

class PlantListConstant {

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
        ),
        Plant(
            id = 3,
            name = "Pilea",
            photo = "https://www.consejosparamihuerto.com/wp-content/uploads/2021/06/pilea-peperomioides.jpg",
            plantRequirement = PlantRequirement(
                watering = "Umiarkowanie",
                fertilization = "Raz w miesiącu",
                rotating = "Nie wymaga",
                soil = "Ziemia przepuszczalna"
            )
        ),
        Plant(
            id = 4,
            name = "Sansevieria",
            photo = "https://www.picturethisai.com/wiki-image/1080/8FCC7DAB46B142EB97D34D349D63E6DD.jpeg",
            plantRequirement = PlantRequirement(
                watering = "Rzadko",
                fertilization = "Co dwa miesiące",
                rotating = "Nie wymaga",
                soil = "Ziemia przepuszczalna"
            )
        ),
        Plant(
            id = 5,
            name = "Spathiphyllum",
            photo = "https://worldoffloweringplants.com/wp-content/uploads/2017/05/Spathiphyllum-wallisii-Peace-Lily1.jpg",
            plantRequirement = PlantRequirement(
                watering = "Regularnie",
                fertilization = "Co miesiąc",
                rotating = "Nie wymaga",
                soil = "Ziemia próchnicza"
            )
        ),
        Plant(
            id = 6,
            name = "Zamioculcas",
            photo = "https://plantis.info/wp-content/uploads/2016/04/Zamioculcas-zamiifolia.jpg",
            plantRequirement = PlantRequirement(
                watering = "Rzadko",
                fertilization = "Co dwa miesiące",
                rotating = "Nie wymaga",
                soil = "Ziemia uniwersalna"
            )
        ),
        Plant(
            id = 7,
            name = "Dracena",
            photo = "https://cdn.shopify.com/s/files/1/0068/4215/5090/products/DracaenaCane_DTL2_1200x1500.jpg?v=1622058878",
            plantRequirement = PlantRequirement(
                watering = "Umiarkowanie",
                fertilization = "Raz w miesiącu",
                rotating = "Nie wymaga",
                soil = "Ziemia przepuszczalna"
            )
        ),
        Plant(
            id = 8,
            name = "Calathea",
            photo = "https://bloomsprouts.com/wp-content/uploads/2021/05/calathea-zebrina.jpg",
            plantRequirement = PlantRequirement(
                watering = "Regularnie",
                fertilization = "Co dwa tygodnie",
                rotating = "Nie wymaga",
                soil = "Ziemia próchnicza"
            )
        ),
        Plant(
            id = 9,
            name = "Aloes",
            photo = "https://agriscaping.com/wp-content/uploads/2020/08/aloe-vera-4733276_1920.jpg",
            plantRequirement = PlantRequirement(
                watering = "Rzadko",
                fertilization = "Co dwa miesiące",
                rotating = "Nie wymaga",
                soil = "Ziemia przepuszczalna"
            )
        ),
        Plant(
            id = 10,
            name = "Paproć",
            photo = "https://www.thespruce.com/thmb/JhHkCLsZj0pVYKCmNhpRUzuyf0U=/2650x1767/filters:fill(auto,1)/Hanging-fern-GettyImages-668761065-591150f25f9b586470eb3f6b.jpg",
            plantRequirement = PlantRequirement(
                watering = "Regularnie",
                fertilization = "Co miesiąc",
                rotating = "Nie wymaga",
                soil = "Ziemia próchnicza"
            )
        ),
        Plant(
            id = 11,
            name = "Grubosz jajowaty",
            photo = "https://www.gardeningknowhow.com/wp-content/uploads/2010/01/jade-plant-1.jpg",
            plantRequirement = PlantRequirement(
                watering = "Rzadko",
                fertilization = "Co dwa miesiące",
                rotating = "Nie wymaga",
                soil = "Ziemia przepuszczalna"
            )
        ),
        Plant(
            id = 12,
            name = "Peperomia",
            photo = "https://i.pinimg.com/originals/66/7a/60/667a60aad67a79441d5f8173e80de2bb.jpg",
            plantRequirement = PlantRequirement(
                watering = "Umiarkowanie",
                fertilization = "Raz w miesiącu",
                rotating = "Nie wymaga",
                soil = "Ziemia przepuszczalna"
            )
        )
    )

    fun getPlantsList() : List<Plant> {
        return samplePlants
    }
}