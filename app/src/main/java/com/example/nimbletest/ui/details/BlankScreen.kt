package com.example.nimbletest.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nimbletest.ui.login.firaSansFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlankScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize().background(Color.LightGray)
    ) {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    }
                ) {
                    Icon(
                        Icons.Rounded.KeyboardArrowLeft,
                        "back",
                        modifier = Modifier.size(40.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Transparent,
                navigationIconContentColor = Color.White
            ),
            modifier = Modifier.padding(top = 30.dp)

        )

        Text(
            text = "Empty Screen", fontFamily = firaSansFamily,
            fontWeight = FontWeight.W800,
            color = Color.White,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}