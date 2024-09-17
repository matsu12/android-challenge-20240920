package com.matsu.dai.android_challenge_20240920

import android.text.TextUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.matsu.dai.android_challenge_20240920.ui.theme.Androidchallenge20240920Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {

    //LaunchedEffectで囲わないと画面遷移が無限ループするので注意
    LaunchedEffect(viewModel.navigationEvent.value) {
        if (viewModel.navigationEvent.value) {
            navController.navigate("Rooms") {
                popUpTo("Login") {
                    inclusive = true
                }
                viewModel.resetState()
            }
        }
    }

    Androidchallenge20240920Theme {
        Scaffold (
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "ログイン画面",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    windowInsets = WindowInsets(
                        top = 0.dp,
                        bottom = 0.dp
                    ),

                    )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    InputScreen(
                        viewModel.tokenText,
                        onTextChanged = { newText -> viewModel.onTextChanged(newText) })
                    LoginButton(onClicked = { viewModel.fetchMe() })
                    ErrorText(viewModel.errorText)
                }
            }
        )
    }

}


@Composable
fun InputScreen(textState: State<String>, onTextChanged: (String) -> Unit) {
    val text = textState.value

    TextField(
        value = text,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done),
        onValueChange = { newText ->
            onTextChanged(newText)
        },
    )
}

@Composable
fun LoginButton(onClicked: () -> Unit) {
    Button(onClick = { onClicked() }) {
        Text("ログインする")
    }
}

@Composable
fun ErrorText(errorTextState: State<String>) {
    Text(errorTextState.value, color = Color.Red)
}
