package com.example.nimbletest.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbletest.ui.login.firaSansFamily
import kotlinx.coroutines.delay
import com.example.nimbletest.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        delay(5000)
        isLoading = false
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 61.dp, start = 20.dp)
                    .fillMaxWidth()
            ) {

                Column {
                    Box(
                        modifier = Modifier
                            .width(117.dp)
                            .height(20.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(20.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 20.dp, top = 15.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                }


            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 130.dp, start = 20.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(20.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Box(
                        modifier = Modifier
                            .width(250.dp)
                            .height(20.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(20.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 60.dp, start = 20.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .width(350.dp)
                            .height(20.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .height(20.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                }
            }

        }


    } else {
        HomeView()
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeView() {
    val pagerState = rememberPagerState(0)
    val color = remember { mutableStateOf(Color.Black) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(count = 3, state = pagerState) { page ->
            when (page) {

                0 -> {
                    color.value = Color.Blue
                }

                1 -> {
                    color.value = Color.Green
                }

                2 -> {
                    color.value = Color.Magenta
                }
            }

            SurveyView(color.value)
        }

        Row(
            modifier = Modifier
                .padding(top = 61.dp, start = 20.dp)
                .fillMaxWidth()
        ) {

            Column {
                Text(
                    text = "MONDAY, JUNE 15",
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.W800,
                    color = Color.White,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "TODAY",
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.W800,
                    color = Color.White,
                    fontSize = 34.sp,
                    lineHeight = 41.sp
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 20.dp, top = 15.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.oval),
                    contentDescription = "",
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 180.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        ) {
            Column {
                DotsIndicator(
                    totalDots = 3,
                    selectedIndex = pagerState.currentPage,
                    selectedColor = Color.White,
                    unSelectedColor = Color.LightGray
                )
                Spacer(modifier = Modifier.size(20.dp))
            }
        }

        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = Color.White,
            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(end = 40.dp, bottom = 50.dp),
            shape = CircleShape
        ) {
            Icon(
                Icons.Filled.KeyboardArrowRight,
                "Survey's Detail",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}


@Composable
fun SurveyView(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 50.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        ) {
            Column {
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = "Working from home Check-In",
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.W800,
                    color = Color.White,
                    fontSize = 28.sp,
                    lineHeight = 34.sp,


                    )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "We would like to know how you feel about our work from home...",
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.W400,
                    color = Color.White,
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                    maxLines = 2,
                    modifier = Modifier.padding(end = 80.dp)
                )
            }
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            }
        }
    }
}