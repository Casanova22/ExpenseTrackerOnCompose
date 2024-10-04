package com.ceej.jc.expensetracker

import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.CheckBox
import androidx.compose.material.icons.rounded.CheckBoxOutlineBlank
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.animation.core.Animatable
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.TextStyle


private const val TAG = "SignUpScreen"

@Composable
fun SignUpScreen(
    userName: String,
    onUserNameChange : (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    confirmPassword: String,
    onPasswordChange : (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    isLoading : Boolean?,
    viewModel: SignInValidationViewModel = viewModel()
) {
    val usernameError by viewModel.usernameError.collectAsStateWithLifecycle()
    val passwordError by viewModel.passwordError.collectAsStateWithLifecycle()
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
            // Progress Bar
            if (isLoading == true) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally)
                )
            }

            // Username Input
            OutlinedTextField(
                value = viewModel.username,

                onValueChange = viewModel::changeUsername,
                label = { Text(
                    "Username",
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.varela_round))),
                    fontSize = 16.sp,) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(20.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    fontSize = 15.sp
                ),

                supportingText = {
                    if (!usernameError.usernameSuccessful) {
                        Text(
                            text = buildString {
                                if (!usernameError.usernameContainsLetters) {
                                    append("Must contain letters.")
                                }
                                if (!usernameError.usernameContainsNumbers) {
                                    append("Must contain Numbers.")
                                }
                                if (!usernameError.usernameContainsSpecialCharacters) {
                                    append("Must contain (., !, _).")
                                }
                            },
                            color = Color.Red
                        )
                    }
                },
                isError = !usernameError.usernameSuccessful
            )

            // Email Input
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text(
                    "Email",
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.varela_round))),
                    fontSize = 16.sp,
                    ) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email),
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    fontSize = 15.sp
                ),

            )

            // Password Input
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = viewModel::changePassword,
                label = { Text(
                    "Password",
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.varela_round))),
                    fontSize = 16.sp,) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else
                        Icons.Filled.VisibilityOff

                    IconButton(onClick = {passwordVisible = !passwordVisible}) {
                        Icon(
                            imageVector = image,
                            contentDescription = null,
                            modifier = Modifier.clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = { passwordVisible = !passwordVisible}
                            )
                        )
                    }
                },
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    fontSize = 15.sp
                )
            )

            // Confirm Password Input
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = { Text(
                    "Confirm Password",
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.varela_round))),
                    fontSize = 16.sp,) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                visualTransformation = if (confirmPasswordVisible)
                    VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Filled.Visibility else
                        Icons.Filled.VisibilityOff

                    IconButton(onClick = {confirmPasswordVisible = !confirmPasswordVisible}) {
                        Icon(
                            imageVector = image,
                            contentDescription = null,
                            modifier = Modifier.clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = { confirmPasswordVisible = !confirmPasswordVisible}
                            )
                        )
                    }
                },
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    fontSize = 15.sp
                )
            )

            Column( modifier = Modifier.padding(top = 20.dp)) {
                ConditionContainer(
                    condition = "must have one uppercase",
                    check = passwordError.hasUppercaseCharacter,
                    isError = password.isNotEmpty() &&  !passwordError.hasUppercaseCharacter
                )

                ConditionContainer(
                    condition = "must have one numeric character",
                    check = passwordError.hasNumericalCharacter,
                    isError = password.isNotEmpty() && !passwordError.hasNumericalCharacter
                )

                ConditionContainer(
                    condition = "must have one special character",
                    check = passwordError.hasSpecialCharacter,
                    isError = password.isNotEmpty() && !passwordError.hasSpecialCharacter
                )

            }

            // Sign Up Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(
                        brush = GradientBrush.mainButtonGradient(),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onSignUpClick().also { Log.d(TAG, "SignupScreen") } },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1F))
            // EULA Privacy Text
            Text(
                text = stringResource(id = R.string.eula_privacy),
                fontFamily = FontFamily(Font(R.font.varela_round)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ConditionContainer(
    condition : String,
    check: Boolean,
    isError : Boolean
) {
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(rotation) {
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

    val icon = if (check) {
        Icons.Rounded.CheckBox
    } else {
        Icons.Rounded.CheckBoxOutlineBlank
    }

    Row{
        Icon(
            imageVector = icon,
            tint = color,
            contentDescription = "status icon",
            modifier = Modifier
                .graphicsLayer(
                    rotationZ = rotation.value
                )
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = condition,
            color = color,
            fontFamily = FontFamily(Font(R.font.varela_round)),
            textAlign = TextAlign.Center
        )
    }
}


