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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.nimbletest.ui.login.firaSansFamily
import com.example.nimbletest.R
import com.example.nimbletest.domain.entities.Survey
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    homeViewModel.onCreate()

    val isLoading by homeViewModel.isLoading.observeAsState(initial = true)

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
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
        HomeView(homeViewModel)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeView(homeViewModel: HomeViewModel) {

    val surveyList by homeViewModel.surveyList.observeAsState(initial = emptyList())
    val pagerState = rememberPagerState(0)

    val surveySelected = remember {
        mutableStateOf(surveyList[0])
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(count = surveyList.size, state = pagerState) { index ->
            when (surveyList[index].title) {
                "Scarlett Bangkok" -> surveySelected.value = surveyList[0]
                "ibis Bangkok Riverside" -> surveySelected.value = surveyList[1]
                "21 on Rajah" -> surveySelected.value = surveyList[2]
                "Let's Chick" -> surveySelected.value = surveyList[3]
                "Health Land Spa" -> surveySelected.value = surveyList[4]
                "Sunset Bar" -> surveySelected.value = surveyList[5]
                "Rice Paper Scissors" -> surveySelected.value = surveyList[6]
                "Shabushi  Buffet" -> surveySelected.value = surveyList[7]
                "Supersports" -> surveySelected.value = surveyList[8]
                "Beach Republic Guest Checkout DEMO" -> surveySelected.value = surveyList[9]
                "Oishi Buffet" -> surveySelected.value = surveyList[10]
                "Segafredo " -> surveySelected.value = surveyList[11]
                "Tops Super Store" -> surveySelected.value = surveyList[12]
                "Veloce Lounge" -> surveySelected.value = surveyList[13]
                "Seafood Market" -> surveySelected.value = surveyList[14]
                "Tree Tops Australia" -> surveySelected.value = surveyList[15]
                "Westside Lounge" -> surveySelected.value = surveyList[16]
                "Alpha Ouzeri" -> surveySelected.value = surveyList[17]
                "Aspira (Main)" -> surveySelected.value = surveyList[18]
                "Punjab Grill" -> surveySelected.value = surveyList[19]
            }

            SurveyView(surveySelected.value, pagerState, index)
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
                .padding(bottom = 150.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        ) {
            Column {
                DotsIndicator(
                    totalDots = surveyList.size,
                    selectedIndex = pagerState.currentPage,
                    selectedColor = Color.White,
                    unSelectedColor = Color.LightGray,
                    pagerState
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


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SurveyView(survey: Survey, pagerState: PagerState, index: Int) {

    val linealGradient = Brush.linearGradient(
        0.0f to Color(0x33000000),
        0.5f to Color(0x50000000),
        1.0f to Color(0x70000000),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffset
                // translate the contents by the size of the page, to prevent the pages from sliding in from left or right and stays in the center
                translationX = pageOffset * size.width
                // apply an alpha to fade the current page in and the old page out
                alpha = 1 - pageOffset.absoluteValue

            }
    ) {
        Image(
            painter = rememberAsyncImagePainter("${survey.coverImageURL}l"),
            contentDescription = "Survey Background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(linealGradient)
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 80.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        ) {
            Column {
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = survey.title,
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.W800,
                    color = Color.White,
                    fontSize = 28.sp,
                    lineHeight = 34.sp,


                    )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = survey.description,
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
    pagerState: PagerState,
) {

    val indicatorScrollState = rememberLazyListState()

    LaunchedEffect(key1 = pagerState.currentPage, block = {
        val currentPage = pagerState.currentPage
        val size = indicatorScrollState.layoutInfo.visibleItemsInfo.size
        val lastVisibleIndex =
            indicatorScrollState.layoutInfo.visibleItemsInfo.last().index
        val firstVisibleItemIndex = indicatorScrollState.firstVisibleItemIndex

        if (currentPage > lastVisibleIndex - 1) {
            indicatorScrollState.animateScrollToItem(currentPage - size + 2)
        } else if (currentPage <= firstVisibleItemIndex + 1) {
            indicatorScrollState.animateScrollToItem((currentPage - 1).coerceAtLeast(0))
        }
    })

    LazyRow(
        modifier = Modifier
            .width((26*3.5).dp)
            .wrapContentHeight(),
        state = indicatorScrollState

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