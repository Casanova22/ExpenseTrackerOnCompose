package com.ceej.common.expensetracker.signup

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.CheckBox
import androidx.compose.material.icons.rounded.CheckBoxOutlineBlank
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.ceej.common.expensetracker.R
import com.ceej.common.expensetracker.modules.ButtonColors
import com.ceej.common.expensetracker.modules.Fonts
import com.ceej.common.expensetracker.signup.component.SignUpComponent

@Composable
fun SignUpScreen(
    component: SignUpComponent,
    viewModel: SignUpViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally)
                )
            }

            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(SignUpEvent.EmailChanged(it)) },
                label = { Text("Email", style = Fonts.appMainFont(), fontSize = 16.sp) },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                shape = RoundedCornerShape(20.dp),
                isError = !state.isEmailValid && state.email.isNotEmpty(),
                supportingText = {
                    if (!state.isEmailValid && state.email.isNotEmpty()) {
                        Text(text = "Invalid email format", color = Color.Red)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                textStyle = Fonts.appMainFont()
            )

            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(SignUpEvent.PasswordChanged(it)) },
                label = { Text("Password", style = TextStyle(fontFamily = FontFamily(Font(R.font.varela_round))), fontSize = 16.sp) },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = !state.passwordError.passwordSuccessful && state.password.isNotEmpty(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                textStyle = Fonts.appMainFont()
            )

            OutlinedTextField(
                value = state.confirmPassword,
                onValueChange = { viewModel.onEvent(SignUpEvent.ConfirmPasswordChanged(it)) },
                label = { Text("Confirm Password", style = Fonts.appMainFont(), fontSize = 16.sp) },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                textStyle = Fonts.appMainFont()
            )

            Column(modifier = Modifier.padding(top = 20.dp)) {
                ConditionContainer(
                    condition = "at least 8 characters",
                    check = state.passwordError.isMinLengthReached,
                    isError = state.password.isNotEmpty() && !state.passwordError.isMinLengthReached
                )
                ConditionContainer(
                    condition = "must have one uppercase",
                    check = state.passwordError.hasUppercaseCharacter,
                    isError = state.password.isNotEmpty() && !state.passwordError.hasUppercaseCharacter
                )
                ConditionContainer(
                    condition = "must have one numeric character",
                    check = state.passwordError.hasNumericalCharacter,
                    isError = state.password.isNotEmpty() && !state.passwordError.hasNumericalCharacter
                )
                ConditionContainer(
                    condition = "must have one special character",
                    check = state.passwordError.hasSpecialCharacter,
                    isError = state.password.isNotEmpty() && !state.passwordError.hasSpecialCharacter
                )
            }

            Spacer(modifier = Modifier.weight(15F))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(brush = ButtonColors.mainButtonGradient(), shape = RoundedCornerShape(20.dp))
                    .clickable { 
                        if (state.isEmailValid && state.passwordError.passwordSuccessful) {
                            component.onSignUpClicked() 
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    style = Fonts.appMainFont(),
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = stringResource(id = R.string.eula_privacy),
                fontFamily = FontFamily(Font(R.font.varela_round)),
                modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ConditionContainer(condition: String, check: Boolean, isError: Boolean) {
    val rotation = remember { Animatable(0f) }
    LaunchedEffect(check) {
        rotation.animateTo(
            targetValue = if (check) 360F else 0F,
            animationSpec = tween(durationMillis = 300)
        )
    }
    val color by animateColorAsState(
        targetValue = when {
            check -> Color(0xFF48908B)
            isError -> Color.Red
            else -> Color(0xFF212121)
        },
        label = "text color"
    )
    val icon = if (check) Icons.Rounded.CheckBox else Icons.Rounded.CheckBoxOutlineBlank
    Row {
        Icon(
            imageVector = icon,
            tint = color,
            contentDescription = "status icon",
            modifier = Modifier.graphicsLayer(rotationZ = rotation.value)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = condition, fontFamily = FontFamily(Font(R.font.varela_round)), textAlign = TextAlign.Center, color = color)
    }
}
