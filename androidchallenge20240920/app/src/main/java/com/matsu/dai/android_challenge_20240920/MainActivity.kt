package com.matsu.dai.android_challenge_20240920

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matsu.dai.android_challenge_20240920.ui.theme.Androidchallenge20240920Theme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Route
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidchallenge20240920Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppScreen(mainViewModel, loginViewModel)
                }
            }
        }
    }

}

@Composable
fun MyAppScreen(mainViewModel: MainViewModel, loginViewModel: LoginViewModel) {
    val navController = rememberNavController() // NavControllerを定義

    Androidchallenge20240920Theme {
        Scaffold(
            bottomBar = { }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = "First Screen",
                modifier = Modifier.padding(padding)
            ) {
                composable(route = "First Screen") {
                    LoginScreen(navController = navController, loginViewModel)
                }
                // 2番目に表示するページ
                composable(route = "Second Screen") {
                    SecondScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    Button(onClick = { navController.navigate("First Screen")}) {
        Text(text = "Go to 1st Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Androidchallenge20240920Theme {
        //MyAppScreen()
    }
}