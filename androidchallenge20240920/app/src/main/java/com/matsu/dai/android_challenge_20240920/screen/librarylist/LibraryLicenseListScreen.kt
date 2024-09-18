package com.matsu.dai.android_challenge_20240920.screen.librarylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.matsu.dai.android_challenge_20240920.R
import kotlin.time.Duration.Companion.nanoseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryLicenseListScreen(viewModel: LibraryLicenseListViewModel, navController: NavController) {

    LaunchedEffect(Unit) {
        viewModel.getLibraryList()
    }

    Scaffold (topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    stringResource(id = R.string.library),
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
            LazyColumn(Modifier.padding(paddingValues)) {
                items(viewModel.libraryList.value) {
                        library ->
                    Column() {
                        Text(text = library.name, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = library.terms, fontSize = 8.sp)
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    )
}