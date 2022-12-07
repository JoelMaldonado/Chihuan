package com.jjmf.chihuancompose.ui.Screens.Login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import androidx.compose.ui.text.style.TextAlign
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
import com.jjmf.chihuancompose.Util.SERVER_ID
import com.jjmf.chihuancompose.ui.theme.ColorP4
import com.jjmf.chihuancompose.ui.theme.ColorS1


@SuppressLint("ResourceType")
@Composable
fun LoginScreen(
    toMenu: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val auth = FirebaseAuth.getInstance()
    var intent by remember { mutableStateOf<Intent?>(null) }
    var menu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (menu){
        LaunchedEffect(key1 = ""){
            toMenu()
        }
    }

    if (auth.currentUser != null){
        viewModel.getId(auth.currentUser?.email!!)
        menu = true
    }

    intent?.let {
        Result(intent = it){user->
            viewModel.insertar(user)
            menu = true
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP4)
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
                .padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "¡Bienvenido a Chihuan!",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Para poder ingresar a chihuan, tienes que ingresar con tu cuenta de Google",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    google(context) {
                        intent = it
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ColorS1),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(imageVector = Icons.Default.Login,
                    contentDescription = null,
                    tint = Color.White)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Ingresar con Google",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

fun google(context: Context, intent: (Intent) -> Unit) {
    val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(SERVER_ID)
        .requestEmail()
        .build()
    val googleClient = GoogleSignIn.getClient(context, googleConf)
    intent(googleClient.signInIntent)
}

@Composable
fun Result(intent: Intent, user:(Usuario)->Unit) {
    val auth = FirebaseAuth.getInstance()
    val res = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {  re ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(re.data!!)
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).addOnSuccessListener {
                val usuario = Usuario(
                    nombres = account.givenName,
                    apellido = account.familyName,
                    foto = account.photoUrl.toString(),
                    correo = account.email
                )
                user(usuario)
            }
        }
    SideEffect {
        res.launch(intent)
    }
}
