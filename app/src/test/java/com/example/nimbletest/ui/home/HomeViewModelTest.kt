package com.example.nimbletest.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nimbletest.domain.ClearPreferencesUseCase
import com.example.nimbletest.domain.GetSurveysUseCase
import com.example.nimbletest.domain.GetTokenUseCase
import com.example.nimbletest.domain.GetUserDataUseCase
import com.example.nimbletest.domain.LogOutUseCase
import com.example.nimbletest.domain.RefreshTokenUseCase
import com.example.nimbletest.domain.SetTokenUseCase
import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.domain.entities.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @RelaxedMockK
    private lateinit var getSurveysUseCase: GetSurveysUseCase

    @RelaxedMockK
    private lateinit var getTokenUseCase: GetTokenUseCase

    @RelaxedMockK
    private lateinit var logOutUseCase: LogOutUseCase

    @RelaxedMockK
    private lateinit var clearPreferencesUseCase: ClearPreferencesUseCase

    @RelaxedMockK
    private lateinit var refreshTokenUseCase: RefreshTokenUseCase

    @RelaxedMockK
    private lateinit var setTokenUseCase: SetTokenUseCase

    @RelaxedMockK
    private lateinit var getUserDataUseCase: GetUserDataUseCase

    lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        homeViewModel =
            HomeViewModel(
                getSurveysUseCase,
                getTokenUseCase,
                logOutUseCase,
                clearPreferencesUseCase,
                refreshTokenUseCase,
                setTokenUseCase,
                getUserDataUseCase
            )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when homeViewModel is created at the first time, get data stored token`() = runTest {
        //given
        val token = "AaBbCcDdEeFfGg"
        coEvery { getTokenUseCase("token") } returns token
        //when
        homeViewModel.onCreate()
        //then
        assert(homeViewModel.token.value == token)
    }

    @Test
    fun `when homeViewModel fetch survey from api an get emptyList`() = runTest {
        //given
        coEvery { getSurveysUseCase("1","1","expiredToken") } returns emptyList()
        //when
        val response = getSurveysUseCase("1","1","expiredToken")
        //then
        assert(response == emptyList<Survey>())
    }

    @Test
    fun `when homeViewModel get empty survey list, refresh token to fetch survey data`() = runTest {
        //given
        coEvery { getSurveysUseCase("1","1","refreshToken") } returns emptyList()
        //when
        homeViewModel.onCreate()
        //then
        coVerify { refreshTokenUseCase("") }
    }

    @Test
    fun `when homeViewModel get user data from api`() = runTest {
        //given
        val userData = User("email", "name", "avatar")
        coEvery { getUserDataUseCase("token") } returns userData
        //when
        var response = getUserDataUseCase("token")
        //then
        assert(response == userData)
    }

    @Test
    fun `when homeViewModel logout user`() = runTest {
        //given
        coEvery { getTokenUseCase("token") } returns "AaBbCCDDe"
        //when
        homeViewModel.onCreate()
        homeViewModel.logOut()
        //then
        coVerify { logOutUseCase("AaBbCCDDe") }
    }
}