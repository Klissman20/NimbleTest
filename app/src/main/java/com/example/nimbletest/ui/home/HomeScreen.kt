package com.example.nimbletest.ui.home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.nimbletest.ui.login.firaSansFamily
import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.domain.entities.User
import com.example.nimbletest.ui.navigation.AppScreens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel) {

    homeViewModel.onCreate()
    val isLoading by homeViewModel.isLoading.observeAsState(initial = true)
    if (isLoading) {
        ShimmerLoading()
    } else {
        HomeView(homeViewModel, navController)
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeView(homeViewModel: HomeViewModel, navController: NavHostController) {

    val surveyList by homeViewModel.surveyList.observeAsState(initial = emptyList())
    val pagerState = rememberPagerState(0)
    val surveySelected = remember {
        mutableStateOf(surveyList[0])
    }

    val userData by homeViewModel.userData.observeAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(drawerShape = RectangleShape) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        DrawerMenu(homeViewModel, navController, scope, userData)
                    }
                }
            },
            gesturesEnabled = true,
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    HorizontalPager(count = surveyList.size, state = pagerState) { index ->

                        //surveySelected.value = surveyList[0]

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
                            "Beach Republic Guest Checkout DEMO" -> surveySelected.value =
                                surveyList[9]

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
                        SurveyView(navController, surveySelected.value, pagerState, index)
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 61.dp, start = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = "MONDAY, NOVEMBER 6",
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
                                painter = rememberAsyncImagePainter(userData?.avatarURL),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        scope.launch {
                                            drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
                                    }
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
                        onClick = {
                            val survey = Uri.encode(
                                Gson().toJson(surveySelected.value)
                            )
                            navController.navigate(
                                AppScreens.DetailScreen.route + "/${survey}"

                            )
                        },
                        containerColor = Color.White,
                        modifier = Modifier
                            .align(
                                Alignment.BottomEnd
                            )
                            .padding(end = 40.dp, bottom = 60.dp),
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
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SurveyView(
    navController: NavHostController,
    survey: Survey,
    pagerState: PagerState,
    index: Int
) {
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
                .fillMaxSize()
                .clickable {
                    val survey = Uri.encode(
                        Gson().toJson(survey)
                    )
                    navController.navigate(
                        AppScreens.DetailScreen.route + "/${survey}"

                    )
                },
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
            .width((26 * 3.5).dp)
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

@Composable
fun DrawerMenu(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    scope: CoroutineScope,
    userData: User?
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xE51E1E1E))
            .padding(20.dp)
            .width(240.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
        ) {
            Text(
                text = userData?.name!!, fontFamily = firaSansFamily,
                fontWeight = FontWeight.W800,
                color = Color.White,
                fontSize = 34.sp,
                lineHeight = 41.sp
            )
            Image(
                painter = rememberAsyncImagePainter(userData.avatarURL),
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp)
                    .weight(1f, true),
                alignment = Alignment.BottomEnd
            )
        }
        Divider(
            thickness = (0.7f).dp,
            color = Color(0x33FFFFFF),
            modifier = Modifier.padding(vertical = 41.dp)
        )
        TextButton(onClick = {
            scope.launch {
                homeViewModel.logOut()
                delay(1000)
                navController.popBackStack()
                navController.navigate(AppScreens.LoginScreen.route)
            }
        }) {
            Text(
                text = "Logout",
                fontFamily = firaSansFamily,
                fontWeight = FontWeight.W400,
                color = Color(0x80FFFFFF),
                fontSize = 20.sp,
                lineHeight = 25.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "v0.1.0 (1562903885)",
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.W400,
            color = Color(0x80FFFFFF),
            fontSize = 11.sp,
            lineHeight = 13.sp,
        )
    }
}