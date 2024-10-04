package com.ceej.jc.expensetracker

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun WrongUsernameAlertDialog(onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Wrong Username")
        },
        text = {
            Text("The Username you entered is incorrect.")
        },
        confirmButton = {
            Button(
                onClick = onDismissRequest
            ) {
                Text("OK")
            }
        }
    )
}


@Composable
fun WrongPasswordAlertDialog(onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Wrong Password")
        },
        text = {
            Text("The password you entered is incorrect.")
        },
        confirmButton = {
            Button(
                onClick = onDismissRequest
            ) {
                Text("OK")
            }
        }
    )
}

@Composable
fun NoInternetAlertDialog(onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Connection error")
        },
        text = {
            Text("Please check your network connection")
        },
        confirmButton = {
            Button(
                onClick = onDismissRequest
            ) {
                Text("OK")
            }
        }
    )
}

@Composable
fun InputUsernameAndPasswordDialog(onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Login Error")
        },
        text = {
            Text("Please enter your username and password.")
        },
        confirmButton = {
            Button(
                onClick = onDismissRequest
            ) {
                Text("OK")
            }
        }
    )
}