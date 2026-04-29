package com.ceej.expensetracker.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ceej.expensetracker.R
import com.ceej.expensetracker.login.component.LoginComponent
import com.ceej.expensetracker.modules.ButtonColors
import com.ceej.expensetracker.modules.Fonts

@Composable
fun LoginScreen(
    component: LoginComponent,
    viewModel: LoginViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.app_bg),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.loginPage_text),
                fontSize = 50.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 50.sp,
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    lineHeight = 60.sp
                )
            )

            Column(
                modifier = Modifier
                    .width(374.dp)
                    .padding(top = 40.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.onEvent(LoginEvent.EmailChanged(it)) },
                    label = { Text(
                        text = "Email",
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.varela_round))),
                        fontSize = 16.sp,
                    )},
                    singleLine = true,
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = Fonts.fontStyleAndSizeForOutlinedTextFields()
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password,
                    onValueChange = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) },
                    shape = RoundedCornerShape(30.dp),
                    label = { Text(
                        text = "Password",
                        style = Fonts.appMainFont(),
                        fontSize = 16.sp
                    ) },
                    textStyle = Fonts.fontStyleAndSizeForOutlinedTextFields(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        IconButton(onClick = { passwordVisible = !passwordVisible}) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    }
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 35.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(
                        elevation = 5.dp, shape = RoundedCornerShape(20.dp), clip = true
                    )
                    .background(
                        brush = ButtonColors.mainButtonGradient(),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { component.onLoginClicked() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.log_in),
                    style = Fonts.appMainFont(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1F))
            Row(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "Don't have an account yet? ", style = Fonts.appMainFont())
                Text(
                    text = "Sign Up",
                    color = Color(0xFF48908B),
                    style = Fonts.appMainFont(),
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { component.onSignUpClicked() }
                )
            }
        }
    }
}
