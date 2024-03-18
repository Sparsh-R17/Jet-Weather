package com.jetapp.jet_weather.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun WeatherAppBar(
    title: String = "TITLE",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController? = null,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
) {
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
                        onClick = {}
                    ) {
                        Icon(Icons.Default.Search,contentDescription = "Search Icon")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More Option")
                    }
                }else{
                    Box {

                    }
                }
            },
            navigationIcon = {
                if (icon != null){
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