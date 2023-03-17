package com.jjmf.chihuancompose.ui.Screens.Login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.Util.show
import com.jjmf.chihuancompose.ui.Features.Login.LoginViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorS1


@SuppressLint("ResourceType")
@Composable
fun LoginScreen(
    toMenu: () -> Unit,
    toRegistro: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    if (viewModel.state.toMenu) {
        LaunchedEffect(key1 = true) {
            toMenu()
        }
    }
    if (viewModel.toMenu) {
        LaunchedEffect(key1 = true) {
            toMenu()
            viewModel.toMenu = false
        }
    }

    viewModel.mensaje?.let {
        LocalContext.current.show(it)
        viewModel.mensaje = null
    }

    val res =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { re ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(re.data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).addOnSuccessListener {
                val usuario = Usuario(
                    nombres = account.givenName,
                    apellido = account.familyName,
                    foto = account.photoUrl.toString(),
                    correo = account.email
                )
                viewModel.insertar(usuario)
            }
        }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_fondo_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Inicia Sesión!",
                fontSize = 24.sp,
                color = ColorP2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = viewModel.correo,
                onValueChange = { viewModel.correo = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                label = {
                    Text(
                        text = "Correo",
                        fontSize = 18.sp,
                        color = ColorP2,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "example@mail.com")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = viewModel.clave,
                onValueChange = { viewModel.clave = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                label = {
                    Text(
                        text = "Contraseña",
                        fontSize = 18.sp,
                        color = ColorP2,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "* * * * * * * * * *")
                }
            )
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = viewModel.check,
                    onCheckedChange = {
                        viewModel.check = it
                    }
                )
                Text(text = "Recuerdame")
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    viewModel.login()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorS1,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (viewModel.loader) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Login,
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Ingresar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(
                onClick = {
                    res.launch(signIn(context))
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.White,
                    contentColor = ColorS1
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(2.dp, ColorS1),

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Login,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Iniciar sesión con google",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "¿No tienes una cuenta? ")
                Text(
                    text = "Registrate",
                    color = ColorS1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { toRegistro() },
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

fun signIn(context: Context): Intent {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    return GoogleSignIn.getClient(context, gso).signInIntent
}