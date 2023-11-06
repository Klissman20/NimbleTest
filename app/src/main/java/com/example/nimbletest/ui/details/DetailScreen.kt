package com.example.nimbletest.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.ui.login.firaSansFamily
import com.example.nimbletest.ui.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, survey: Survey) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = rememberAsyncImagePainter(survey.coverImageURL + "l"),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .scale(1.4f),
            contentScale = ContentScale.Crop
        )
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
        Column(modifier = Modifier.padding(top = 100.dp, start = 20.dp)) {
            Text(
                text = survey.title,
                fontFamily = firaSansFamily,
                fontWeight = FontWeight.W800,
                color = Color.White,
                fontSize = 34.sp,
                lineHeight = 41.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = survey.description,
                fontFamily = firaSansFamily,
                fontWeight = FontWeight.W400,
                color = Color.White,
                fontSize = 17.sp,
                lineHeight = 22.sp
            )
        }
        Button(
            onClick = { navController.navigate(AppScreens.BlankDetailsScreen.route) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 60.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                disabledContainerColor = Color.Gray
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Start Survey",
                fontFamily = firaSansFamily,
                fontWeight = FontWeight.W800,
                color = Color.Black,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                maxLines = 1
            )
        }
    }
}