package com.matsu.dai.android_challenge_20240920

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.matsu.dai.android_challenge_20240920.data.repo.model.Room

@Composable
fun ChatRoomListScreen(navController: NavController, viewModel: ChatRoomListViewModel) {
    viewModel.getRoomList()
    Row {
        ItemListScreen(viewModel.rooms.value, navController)
    }
}


@Composable
fun ItemListScreen(rooms: List<Room>, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(rooms) { room ->
            RoomItem(room = room, navController = navController)
        }
    }
}

@Composable
fun RoomItem(room: Room, navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth().clickable {
            navController.navigate("detail/${room.roomId}")
        },
        verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier.size(60.dp).padding(start = 10.dp),
            model = room.iconPath,
            contentDescription = null,
        )
        Text(
            text = "${room.name}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(end = 10.dp)
        )
    }
    HorizontalDivider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
}
