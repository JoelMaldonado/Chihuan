package com.jjmf.chihuancompose.ui.Features.Registro

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Util.show
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorS1

@Composable
fun RegistroScreen(
    toMenu:()->Unit,
    viewModel: RegistroViewModel = hiltViewModel(),
) {
    val focus = LocalFocusManager.current

    viewModel.error?.let {
        LocalContext.current.show(it)
        viewModel.error = null
    }

    if (viewModel.pass){
        LaunchedEffect(key1 = true){
            toMenu()
            viewModel.pass = false
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(text = "Registro", fontSize = 24.sp, color = ColorP2, fontWeight = FontWeight.Bold)
        CajaTexto(
            valor = viewModel.nombre,
            newValor = { viewModel.nombre = it },
            label = "Nombres",
            placeholder = "Ingresa aqui tu nombre",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            )
        )
        CajaTexto(
            valor = viewModel.apellidos,
            newValor = { viewModel.apellidos = it },
            label = "Apellidos",
            placeholder = "Ingresa aqui tus apellidos",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            )
        )
        CajaTexto(
            valor = viewModel.correo,
            newValor = {
                viewModel.correo = it
            },
            label = "Correo",
            placeholder = "ejemplo@mail.com",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Email,
            textoError = if (viewModel.correo.isEmail()) null else "Ingrese un correo valido"
        )
        val bool = remember { mutableStateOf(false) }
        CajaTexto(
            valor = viewModel.pass1,
            newValor = { viewModel.pass1 = it },
            label = "Contraseña",
            placeholder = "* * * * * * * *",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Password,
            trailingIcon = {
                IconButton(onClick = { bool.value = !bool.value }) {
                    val icon =
                        if (bool.value) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    Icon(imageVector = icon, contentDescription = null, tint = ColorP2)
                }
            },
            visual = if (bool.value) VisualTransformation.None else PasswordVisualTransformation()
        )
        CajaTexto(
            valor = viewModel.pass2,
            newValor = { viewModel.pass2 = it },
            label = "Confirma tu contraseña",
            placeholder = "* * * * * * * *",
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    focus.clearFocus()
                }
            ),
            keyboardType = KeyboardType.Password,
            textoError = if (viewModel.pass1 == viewModel.pass2) null else "Las contraseñas no son iguales",
            trailingIcon = {
                if (viewModel.pass1 == viewModel.pass2 && viewModel.pass1.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ColorP2
                    )
                }
            },
            visual = PasswordVisualTransformation()
        )
        Button(
            onClick = {
                viewModel.registrar()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = viewModel.nombre.isNotEmpty() &&
                    viewModel.apellidos.isNotEmpty() &&
                    viewModel.correo.isEmail() &&
                    viewModel.pass1.isNotEmpty() &&
                    viewModel.pass2.isNotEmpty() &&
                    viewModel.pass1 == viewModel.pass2,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ColorS1
            )
        ) {
            if (viewModel.loader) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            } else {
                Text(text = "Crear cuenta")
            }
        }
    }


}


@Composable
fun CajaTexto(
    modifier: Modifier = Modifier,
    valor: String,
    newValor: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    textoError: String? = null,
    visual: VisualTransformation = VisualTransformation.None,
) {
    Column {
        TextField(
            value = valor,
            onValueChange = {
                newValor(it)
            },
            modifier = modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            ),
            label = {
                Text(text = label)
            },
            placeholder = {
                Text(text = placeholder)
            },
            trailingIcon = trailingIcon,
            visualTransformation = visual
        )
        textoError?.let {
            if (valor.isNotEmpty()) {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }
}

private fun String.isEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}