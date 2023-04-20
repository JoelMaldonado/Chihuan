package com.jjmf.chihuancompose.ui.Screens.Login

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.*
import com.jjmf.chihuancompose.Core.GoogleAuthUiClient
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.Util.show
import com.jjmf.chihuancompose.ui.Features.Login.LoginViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorS1
import kotlinx.coroutines.launch


@SuppressLint("ResourceType")
@Composable
fun LoginScreen(
    toMenu: () -> Unit,
    toRegistro: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val auth = FirebaseAuth.getInstance()

    val context = LocalContext.current

    val coroutine = rememberCoroutineScope()

    val googleAuthUiClient = GoogleAuthUiClient(
        context = context,
        oneTapClient = Identity.getSignInClient(context)
    )
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

    val res = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = {
            if (it.resultCode == RESULT_OK) {
                coroutine.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = it.data ?: return@launch
                    )
                    if (signInResult.data != null) {
                        viewModel.insertar(signInResult.data)
                    }
                }
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP2)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 60.dp)
        ) {

            Text(
                text = "¡Inicia Sesión!",
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Ingresa tu correo y contraseña para iniciar sesion, en caso de contar con cuenta de google puedes iniciar con tu cuenta",
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .shadow(5.dp, shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CajaTextoLogin(
                valor = viewModel.correo,
                newValor = {
                    viewModel.correo = it
                },
                placeholder = "Correo"
            )
            CajaTextoLogin(
                valor = viewModel.clave,
                newValor = {
                    viewModel.clave = it
                },
                placeholder = "Contraseña",
                isPass = true
            )
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = ColorS1,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { }
            )
            Spacer(modifier = Modifier.height(10.dp))
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
            Button(
                onClick = {
                    coroutine.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        res.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Text(text = "Continua con Google", modifier = Modifier.padding(horizontal = 15.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_right),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )
            }

            /*
            val context = LocalContext.current as Activity
            Button(
                onClick = {
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+51989076570")       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(context)
                        .setCallbacks(object :
                            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                                signInWithPhoneAuthCredential(p0, context)
                                context.show("Si entro")

                            }

                            override fun onVerificationFailed(p0: FirebaseException) {
                                context.show("No entro")
                            }

                        })
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.Blue
                )
                Text(
                    text = "Continua con tu teléfono",
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_right),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )
            }*/
            Spacer(modifier = Modifier.weight(1f))
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

private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, activity: Activity) {
    val auth = FirebaseAuth.getInstance()
    auth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                val user = task.result?.user
                Log.d("tagitoUser", user.toString())
            } else {
                Log.d("tagitoUser", task.exception.toString())
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                }
            }
        }
}

@Composable
fun CajaTextoLogin(
    valor: String,
    newValor: (String) -> Unit,
    placeholder: String,
    isPass: Boolean = false,
) {

    val isVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Gray.copy(0.1f))
            .padding(start = 30.dp, end = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = valor,
            onValueChange = newValor,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (isVisible.value) VisualTransformation.Companion.None else PasswordVisualTransformation(),
            singleLine = true,
            maxLines = 1
        )
        if (valor.isEmpty()) {
            Text(text = placeholder, color = Color.Gray)
        }
        if (isPass) {
            IconButton(
                onClick = {
                    isVisible.value = !isVisible.value
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                val icon =
                    if (isVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff
                Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
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