package com.example.plantit.core.di

import com.example.plantit.core.domain.use_case.get_access_token.GetAccessTokenUseCase
import com.example.plantit.core.domain.use_case.get_saved_user.GetSavedUserUseCase
import com.example.plantit.core.domain.use_case.is_user_logged_in.IsUserLoggedInUseCase
import com.example.plantit.core.domain.use_case.logout.LogoutUseCase
import com.example.plantit.features.login.domain.use_case.login.LoginUseCase
import com.example.plantit.core.domain.use_case.save_auth_info.SaveAuthInfoUseCase
import com.example.plantit.core.domain.use_case.session_valid.IsSessionValidUseCase
import com.example.plantit.features.login.domain.use_case.sign_up.SignUpUseCase
import com.example.plantit.features.plant_detail.domain.use_case.GetPlantDetailByIdUseCase
import com.example.plantit.features.plant_search.domain.use_case.get_plants.GetAllPlantsUseCase
import com.example.plantit.features.plant_search.domain.use_case.search_plants.SearchPlantsUseCase
import com.example.plantit.features.user_plants.domain.use_case.add_user_plant.AddUserPlantUseCase
import com.example.plantit.features.user_plants.domain.use_case.get_user_plants.GetUserPlantsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetAllPlantsUseCase(get()) }
    single { SearchPlantsUseCase(get()) }
    single { GetPlantDetailByIdUseCase(get()) }
    single { AddUserPlantUseCase(get()) }
    single { LoginUseCase(get()) }
    single { SignUpUseCase(get()) }
    single { SaveAuthInfoUseCase(get()) }
    single { GetSavedUserUseCase(get()) }
    single { GetAccessTokenUseCase(get()) }
    single { IsUserLoggedInUseCase(get()) }
    single { IsSessionValidUseCase(get()) }
    single { LogoutUseCase(get()) }
    single { GetUserPlantsUseCase(get()) }


}
