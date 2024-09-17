package com.matsu.dai.android_challenge_20240920.screen.detail

import android.text.TextUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.matsu.dai.android_challenge_20240920.data.repo.model.Message
import com.matsu.dai.android_challenge_20240920.ui.theme.Androidchallenge20240920Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomDetailScreen(viewModel: RoomDetailViewModel, roomId: Int, navController: NavController) {
    LaunchedEffect(roomId) {
        viewModel.fetchRoomData(roomId)
    }
    Androidchallenge20240920Theme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            viewModel.roomName.value,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    windowInsets = WindowInsets(
                        top = 0.dp,
                        bottom = 0.dp
                    ),

                    )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    ItemListScreen(viewModel.messages.value)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MessageInputScreen(
                            textState = viewModel.messageText,
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentWidth()
                        ) { newText ->
                            viewModel.onTextChanged(newText)
                        }
                        MessageSendButton(!TextUtils.isEmpty(viewModel.messageText.value)) {
                            viewModel.sendMessage()
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun ItemListScreen(messages: List<Message>) {
    LazyColumn(modifier = Modifier.fillMaxHeight(0.92f)) {
        items(messages) { message ->
            MessageItem(message = message)
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Row(modifier = Modifier
        .fillMaxWidth()) {
        AsyncImage(
            alignment = Alignment.TopStart,
            modifier = Modifier
                .size(60.dp)
                .padding(start = 10.dp),
            model = message.account?.avatarImageUrl ?: "",
            contentDescription = null,
        )
        Text(
            text = "${message.body}",
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp)
        )
    }
    HorizontalDivider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun MessageInputScreen(textState: State<String>, modifier: Modifier,  onTextChanged: (String) -> Unit) {
    val text = textState.value

    TextField(
        modifier = modifier
            .heightIn(min = 56.dp, max = 150.dp),
        value = text,
        maxLines = 3,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done),
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp
        ),
        onValueChange = { newText ->
            onTextChanged(newText)
        },
    )
}

@Composable
fun MessageSendButton(isSendEnabled: Boolean, onClicked: () -> Unit) {
    Button(enabled = isSendEnabled, onClick = { onClicked() }) {
        Text("送信する")
    }
}
