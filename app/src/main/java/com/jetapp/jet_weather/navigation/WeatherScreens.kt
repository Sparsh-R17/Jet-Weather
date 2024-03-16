package com.jetapp.jet_weather.navigation

enum class WeatherScreens {
    SplashScreen,
    MainScreen,
    AboutScreen,
    FavoriteScreen,
    SearchScreen,
    SettingScreen;
    companion object{
        fun fromRoute(route: String?): WeatherScreens
                = when(route?.substringBefore("/")){
            SplashScreen.name -> SplashScreen
            MainScreen.name -> MainScreen
            AboutScreen.name -> AboutScreen
            FavoriteScreen.name -> FavoriteScreen
            SearchScreen.name -> SearchScreen
            SettingScreen.name -> SettingScreen
            null -> SplashScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}