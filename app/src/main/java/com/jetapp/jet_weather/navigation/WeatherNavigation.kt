package com.jetapp.jet_weather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetapp.jet_weather.screens.main.MainScreen
import com.jetapp.jet_weather.screens.main.MainViewModel
import com.jetapp.jet_weather.screens.search.SearchScreen
import com.jetapp.jet_weather.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType //this is for specifying the type of argument we are sending\\
                }
            )
        ){ navBack ->
            navBack.arguments?.getString("city").let { cityName ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    city = cityName
                )
            }
        }
        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }
    }
}

