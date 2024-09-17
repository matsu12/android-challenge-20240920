package com.matsu.dai.android_challenge_20240920.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.matsu.dai.android_challenge_20240920.R
import com.matsu.dai.android_challenge_20240920.data.repo.model.Room
import com.matsu.dai.android_challenge_20240920.ui.theme.Androidchallenge20240920Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomListScreen(navController: NavController, viewModel: ChatRoomListViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getRoomList()
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
                            stringResource(id = R.string.room_list),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        Button(onClick = {
                            viewModel.onLogout()
                            navController.navigate("Login") {
                                popUpTo("Rooms") {
                                    inclusive = true
                                }
                            }
                        }) {
                            Text(text = stringResource(id = R.string.logout))
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
                    modifier = Modifier.padding(paddingValues)
                ) {
                    ItemListScreen(viewModel.rooms.value, navController)
                    RoomsErrorText(errorTextState = viewModel.errorText)
                }
            }
        )
    }

}


@Composable
fun ItemListScreen(rooms: List<Room>, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxHeight(0.9f)) {
        items(rooms) { room ->
            RoomItem(room = room, navController = navController)
        }
    }
}

@Composable
fun RoomItem(room: Room, navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate("RoomDetail/${room.roomId}")
        },
        verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .padding(start = 10.dp),
            model = room.iconPath,
            contentDescription = null,
        )
        Text(
            text = "${room.name}",
            maxLines = 1,
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
fun RoomsErrorText(errorTextState: State<String>) {
    Text(errorTextState.value, color = Color.Red)
}
