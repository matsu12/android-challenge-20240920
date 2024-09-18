package com.matsu.dai.android_challenge_20240920.screen

import android.os.Bundle
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matsu.dai.android_challenge_20240920.screen.detail.RoomDetailScreen
import com.matsu.dai.android_challenge_20240920.screen.detail.RoomDetailViewModel
import com.matsu.dai.android_challenge_20240920.screen.librarylist.LibraryLicenseListScreen
import com.matsu.dai.android_challenge_20240920.screen.librarylist.LibraryLicenseListViewModel
import com.matsu.dai.android_challenge_20240920.screen.list.ChatRoomListScreen
import com.matsu.dai.android_challenge_20240920.screen.list.ChatRoomListViewModel
import com.matsu.dai.android_challenge_20240920.screen.login.LoginScreen
import com.matsu.dai.android_challenge_20240920.screen.login.LoginViewModel
import com.matsu.dai.android_challenge_20240920.ui.theme.Androidchallenge20240920Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var loginViewModel: LoginViewModel
    @Inject
    lateinit var chatRoomListViewModel: ChatRoomListViewModel
    @Inject
    lateinit var roomDetailViewModel: RoomDetailViewModel
    @Inject
    lateinit var libraryLicenseListViewModel: LibraryLicenseListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidchallenge20240920Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppScreen(innerPadding, mainViewModel, loginViewModel, chatRoomListViewModel, roomDetailViewModel, libraryLicenseListViewModel)
                }
            }
        }
    }

}

@Composable
fun MyAppScreen(innerPadding: PaddingValues, mainViewModel: MainViewModel, loginViewModel: LoginViewModel,
                chatRoomListViewModel: ChatRoomListViewModel,
                roomDetailViewModel: RoomDetailViewModel, libraryLicenseListViewModel: LibraryLicenseListViewModel
) {
    val navController = rememberNavController()
    val notLogin = TextUtils.isEmpty(mainViewModel.localTokenText.value)
    val firstScreen = remember(notLogin) {
        if (notLogin) {
            "Login"
        } else {
            "Rooms"
        }
    }
    Androidchallenge20240920Theme {
        Scaffold(
            modifier = Modifier.padding(innerPadding),
            bottomBar = { }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = firstScreen,
                modifier = Modifier.padding(padding)
            ) {
                composable(route = "Login") {
                    LoginScreen(navController = navController, loginViewModel)
                }
                composable(route = "Rooms") {
                    ChatRoomListScreen(navController = navController, chatRoomListViewModel)
                }
                composable("RoomDetail/{roomId}") { backStackEntry ->
                    val roomId = backStackEntry.arguments?.getString("roomId")?.toIntOrNull() ?: 0
                    RoomDetailScreen(viewModel = roomDetailViewModel, roomId = roomId, navController = navController)
                }
                composable(route = "LibraryList") {
                    LibraryLicenseListScreen(libraryLicenseListViewModel, navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Androidchallenge20240920Theme {
        //MyAppScreen()
    }
}