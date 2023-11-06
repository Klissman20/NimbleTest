package com.example.nimbletest.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.util.PatternsCompat
import com.example.nimbletest.domain.ForgotPasswordUseCase
import com.example.nimbletest.domain.GetTokenUseCase
import com.example.nimbletest.domain.LoginUseCase
import com.example.nimbletest.domain.SetTokenUseCase
import com.example.nimbletest.domain.entities.Auth
import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @RelaxedMockK
    private lateinit var loginUseCase: LoginUseCase

    @RelaxedMockK
    private lateinit var getTokenUseCase: GetTokenUseCase

    @RelaxedMockK
    private lateinit var setTokenUseCase: SetTokenUseCase

    @RelaxedMockK
    private lateinit var forgotPasswordUseCase: ForgotPasswordUseCase

    lateinit var loginViewModel: LoginViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        loginViewModel =
            LoginViewModel(loginUseCase, getTokenUseCase, setTokenUseCase, forgotPasswordUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when loginViewModel is created at the first time and has stored token to navigate to home`() = runTest {
        //given
        val token = "AaBbCcDdEeFfGg"
        coEvery { getTokenUseCase("token") } returns token
        //when
        loginViewModel.onCreate("token")
        //then
        assert(loginViewModel.hasToken.value == true)
    }

    @Test
    fun `when loginViewModel is created at the first time, get data stored token is null or empty`() = runTest {
        //given
        coEvery { getTokenUseCase("token") } returns null
        //when
        loginViewModel.onCreate("token")
        //then
        assert(loginViewModel.hasToken.value == null)
    }

    @Test
    fun `when loginViewModel on login button is selected and has valid credentials`() = runTest {
        //given
        val validAuth = Auth("asdf43wfedfd","Bearer",1000,"sdfsefsdfwsef")
        mockkObject(PatternsCompat.EMAIL_ADDRESS)
        coEvery {
            PatternsCompat.EMAIL_ADDRESS.matcher(any()).matches()
        }returns true
        coEvery {
            loginViewModel.onLoginChanged("esteban@example.com","12345678")
        }
        coEvery {
            loginUseCase("esteban@example.com", "12345678")
        } returns validAuth
        //when
        loginViewModel.onLoginSelected()
        //then
        assert(loginViewModel.hasToken.value == true)
    }

    @Test
    fun `when loginViewModel on login button is selected and has bad credentials`() = runTest {
        //given
        val emptyAuth = Auth("","",0,"")
        mockkObject(PatternsCompat.EMAIL_ADDRESS)
        coEvery {
            PatternsCompat.EMAIL_ADDRESS.matcher(any()).matches()
        }returns true
        coEvery {
            loginViewModel.onLoginChanged("bad@email.com","098976534")
        }
        coEvery {
            loginUseCase("bad@email.com", "bad@email.com")
        } returns emptyAuth
        //when
        loginViewModel.onLoginSelected()
        //then
        assert(loginViewModel.hasToken.value == null)
    }

    @Test
    fun `when loginViewModel on reset password is selected`() = runTest {
        //given
        mockkObject(PatternsCompat.EMAIL_ADDRESS)
        coEvery {
            PatternsCompat.EMAIL_ADDRESS.matcher(any()).matches()
        }returns true
        coEvery {
            loginViewModel.onLoginChanged("forgot@password.com", "")
        }
        //when
        loginViewModel.onResetPasswordSelected()
        //then
        coVerify { forgotPasswordUseCase("forgot@password.com") }
    }

}