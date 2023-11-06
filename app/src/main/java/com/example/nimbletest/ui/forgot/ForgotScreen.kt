package com.example.nimbletest.ui.forgot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nimbletest.ui.login.CoverBackground
import com.example.nimbletest.ui.login.Email
import com.example.nimbletest.ui.login.ImageLogo
import com.example.nimbletest.ui.login.LoginViewModel
import com.example.nimbletest.ui.login.firaSansFamily
import androidx.compose.runtime.livedata.observeAsState
import com.example.nimbletest.R

@Composable
fun ForgotScreen(navController: NavController, loginViewModel: LoginViewModel) {
    ForgotView(navController, loginViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotView(navController: NavController, loginViewModel: LoginViewModel) {

    val email: String by loginViewModel.email.observeAsState(initial = "")

    Box(modifier = Modifier.fillMaxSize()) {
        CoverBackground(R.drawable.background)
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
        ImageLogo(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 141.dp)
        )
        Text(
            text = "Enter your email to receive instructions for resetting your password.",
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.W400,
            color = Color.LightGray,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            modifier = Modifier
                .align(
                    Alignment.TopCenter
                )
                .padding(top = 205.dp),
            textAlign = TextAlign.Center
        )
        Column(Modifier.align(Alignment.Center)) {
            Email(email) {
                loginViewModel.onLoginChanged(email = it,"")
            }
            Spacer(modifier = Modifier.size(20.dp))
            ResetButton(loginViewModel)
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun ResetButton(loginViewModel: LoginViewModel) {
    Button(
        onClick = { loginViewModel.onResetPasswordSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Reset",
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.W800,
            fontSize = 17.sp,
            lineHeight = 22.sp
        )
    }
}
