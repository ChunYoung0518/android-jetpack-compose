package com.example.jetpack_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun AlertDialogScreen() {
    Column {
        val showDialog = remember { mutableStateOf(false) }

        Button(onClick = {
            showDialog.value = true
        }) {
            Text("Display Alert")
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text(text = "Alert!") },
                text = { Text("Something went wrong") },
                confirmButton = {
                    Button(onClick = { showDialog.value = false }) {
                        Text(text = "OK")
                    }
                }
            )
        }
    }

}