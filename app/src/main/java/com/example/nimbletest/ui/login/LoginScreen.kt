package com.example.nimbletest.ui.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.nimbletest.R
import com.example.nimbletest.ui.navigation.AppScreens


@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavHostController) {
    loginViewModel.onCreate()
    Box(
        Modifier
            .fillMaxSize()
    ) {
        val hasToken: Boolean by loginViewModel.hasToken.observeAsState(initial = false)
        if (hasToken) {
            navController.popBackStack()
            navController.navigate(AppScreens.HomeScreen.route)
        } else {
            CoverBackground()
            ImageLogo(
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 153.dp)
            )
            Body(Modifier.align(Alignment.Center), loginViewModel, navController)
        }
    }
}

@Composable
fun CoverBackground() {

    val linealGradient = Brush.linearGradient(
        0.0f to Color(0x33000000),
        0.5f to Color(0x80000000),
        1.0f to Color(0xFF000000),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .blur(30.dp),
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(linealGradient)
        )
    }
}

val firaSansFamily = FontFamily(
    Font(R.font.neuzeit_s_lt_std_book)
)

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel, navController: NavHostController) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)

    Column(modifier = modifier) {
        Email(email) {
            loginViewModel.onLoginChanged(email = it, password)
        }
        Spacer(modifier = Modifier.size(20.dp))
        Password(password, navController) {
            loginViewModel.onLoginChanged(email, password = it)
        }
        Spacer(modifier = Modifier.size(20.dp))
        LoginButton(isLoginEnable, loginViewModel)
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
fun LoginButton(
    loginEnable: Boolean,
    loginViewModel: LoginViewModel
) {
    Button(
        onClick = {
            loginViewModel.onLoginSelected()
        },
        enabled = loginEnable,
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
            text = "Log in",
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.W800,
            fontSize = 17.sp,
            lineHeight = 22.sp
        )
    }
}

@Composable
fun ForgotPassword(navController: NavController) {

    Text(
        text = "Forgot?",
        color = Color.Gray,
        modifier = Modifier
            .padding(end = 10.dp)
            .clickable { navController.navigate(AppScreens.ForgotScreen.route) },
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        lineHeight = 20.sp
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, navController: NavController, onTextChange: (String) -> Unit) {

    TextField(
        value = password,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            containerColor = Color.DarkGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            placeholderColor = Color.Gray,
            cursorColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            ForgotPassword(navController)
        },
        visualTransformation = PasswordVisualTransformation(),
        textStyle = TextStyle(
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.W400,
            fontSize = 17.sp,
            lineHeight = 22.sp
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChange: (String) -> Unit) {

    TextField(
        value = email,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            containerColor = Color.DarkGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            placeholderColor = Color.Gray,
            cursorColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.W400,
            fontSize = 17.sp,
            lineHeight = 22.sp
        )
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_white),
        contentDescription = "logo",
        modifier = modifier
    )
}