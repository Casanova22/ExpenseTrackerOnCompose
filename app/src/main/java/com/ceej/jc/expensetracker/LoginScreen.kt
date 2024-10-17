@file:OptIn(ExperimentalMaterial3Api::class)

package com.ceej.jc.expensetracker

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ceej.jc.expensetracker.modules.GradientBrush


private const val TAG = "LoginScreen"

@Suppress("DEPRECATION")
@Composable
fun LoginScreen(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,

) {

    val isError : Boolean? = null
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.app_bg), // Background Image
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            // Login Text
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
                    lineHeight = 60.sp // Increase line height to prevent overlap
                )
            )

            // Login Form
            Column(
                modifier = Modifier
                    .width(374.dp)
                    .padding(top = 40.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                //Username
                OutlinedTextField(
                    value = username,
                    onValueChange = onUsernameChange,
                    label = { Text(
                        text = "Username",
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.varela_round))),
                        fontSize = 16.sp,
                    )},
                    singleLine = true,
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.varela_round)),
                        fontSize = 15.sp
                    )
                )

                //Password
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = onPasswordChange,

                    shape = RoundedCornerShape(30.dp),
                    label = { Text(
                        text = "Password",
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.varela_round))),
                        fontSize = 16.sp
                    ) },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.varela_round)),
                        fontSize = 15.sp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        IconButton(onClick = { passwordVisible = !passwordVisible}) {
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
                    }
                )
            }

            // Login Button
            Box(
                modifier = Modifier
                    .padding(top = 35.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(
                        elevation = 5.dp, shape = RoundedCornerShape(20.dp), clip = true
                    )
                    .background(
                        brush = GradientBrush.mainButtonGradient(),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { onLoginClick() },
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = stringResource(id = R.string.log_in),
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1F))
            // Sign-up Text
            Row(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Don't have an account yet? ",
                    fontFamily = FontFamily(Font(R.font.varela_round))
                )
                Text(
                    text = "Sign Up",
                    color = Color(0xFF48908B),
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onSignUpClick().also { Log.d(TAG, "LoginScreen")} }
                )
            }
        }
    }
}
