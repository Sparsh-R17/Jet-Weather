package com.jetapp.jet_weather.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.NavController
import com.jetapp.jet_weather.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "TITLE",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    if (showDialog.value) {
        ShowDropDownMenu(
            showDialog = showDialog,
            navController = navController,
        )
    }
    Surface(
        shadowElevation = elevation
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                    )
                )
            },
            actions = {
                if (isMainScreen) {
                    IconButton(
                        onClick = {
                            onAddActionClicked.invoke()
                        }
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                    }
                    IconButton(onClick = {
                        showDialog.value = true
                    }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More Option")
                    }
                } else {
                    Box {

                    }
                }
            },
            navigationIcon = {
                if (icon != null) {
                    Icon(
                        modifier = Modifier.clickable {
                            onButtonClicked.invoke()  //act as a callback
                        },
                        imageVector = icon,
                        contentDescription = "Navigation Icon",
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary
            ),
        )
    }
}

@Composable
fun ShowDropDownMenu(
    showDialog: MutableState<Boolean>,
    navController: NavController
) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf(
        "About",
        "Favorites",
        "Settings",
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            modifier = Modifier
                .width(145.dp),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {
            items.fastForEachIndexed { index, text ->
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = when (text) {
                                "About" -> Icons.Outlined.Info
                                "Favorites" -> Icons.Outlined.Favorite
                                else -> Icons.Outlined.Settings
                            },
                            contentDescription = "Menu Icon"
                        )
                    },
                    text = { Text(text = text) },
                    onClick = {
                        expanded = false
                        showDialog.value = false
                        navController.navigate(
                            when (text) {
                                "About" -> WeatherScreens.AboutScreen.name
                                "Favorites" -> WeatherScreens.FavoriteScreen.name
                                else -> WeatherScreens.SettingScreen.name
                            },
                        )
                    }
                )
            }
        }
    }
}
