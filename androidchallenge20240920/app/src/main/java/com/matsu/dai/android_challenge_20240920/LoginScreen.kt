package com.matsu.dai.android_challenge_20240920

import android.text.TextUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.matsu.dai.android_challenge_20240920.ui.theme.Androidchallenge20240920Theme

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {

    if (!TextUtils.isEmpty(viewModel.localTokenText.value)) {
        navController.navigate("Second Screen")
        return
    }

    Androidchallenge20240920Theme {
        Column {
            InputScreen(viewModel.tokenText, onTextChanged ={ newText -> viewModel.onTextChanged(newText) })
            LoginButton(onClicked = { viewModel.fetchMe() })
            ErrorText(viewModel.errorText)
        }
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
