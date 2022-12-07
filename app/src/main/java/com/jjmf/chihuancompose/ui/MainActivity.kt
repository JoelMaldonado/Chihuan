package com.jjmf.chihuancompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.chihuancompose.ui.Screens.Login.LoginScreen
import com.jjmf.chihuancompose.ui.Screens.Menu.Menu
import com.jjmf.chihuancompose.ui.Screens.Movimiento.MovimientoScreen
import com.jjmf.chihuancompose.ui.Screens.SplashScreen
import com.jjmf.chihuancompose.ui.theme.ChihuanComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChihuanComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") {
                        SplashScreen {
                            navController.navigate("login")
                        }
                    }
                    composable("login") {
                        LoginScreen(
                            toMenu = {
                                navController.navigate("menu")
                            }
                        )
                    }
                    composable("menu") {
                        Menu(
                            toMovimiento = {
                                navController.navigate("movimiento")
                            }
                        )
                    }
                    composable("movimiento"){
                        MovimientoScreen()
                    }
                }
            }
        }
    }
}


@Composable
fun Numeros() {
    var monto by remember { mutableStateOf(TextFieldValue(text = "0", selection = TextRange(1))) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Monto", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 30.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(text = "S/", fontSize = 50.sp)
            BasicTextField(
                value = monto,
                onValueChange = {

                },
                textStyle = TextStyle(fontSize = 40.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            shape = RoundedCornerShape(20.dp),
            enabled = monto.text.toDouble() > 0.0
        ) {
            Text(text = "Correcto")
        }
    }
}