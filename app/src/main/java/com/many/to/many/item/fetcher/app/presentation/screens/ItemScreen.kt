package com.many.to.many.item.fetcher.app.presentation.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.many.to.many.item.fetcher.app.presentation.vm.ItemViewModel
import com.many.to.many.item.fetcher.app.ui.compomemts.CustomLoader
import com.many.to.many.item.fetcher.app.ui.compomemts.ItemCard
import com.many.to.many.item.fetcher.app.ui.compomemts.SvgIcon
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(navController: NavController, viewModel: ItemViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (state.items.isNotEmpty()) {
                            Text(
                                text = state.category,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFFF4081),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { viewModel.fetchItems() }) {
                        SvgIcon(modifier = Modifier.scale(0.92f))
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    CustomLoader()
                }
            }
            state.error != null -> {
                Text(
                    text = state.error ?: "Unknown error",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.White)
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.White)
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(state.items) { item ->
                        var visible by remember { mutableStateOf(false) }
                        LaunchedEffect(Unit) {
                            visible = true
                        }

                        AnimatedVisibility(
                            visible = visible,
                            enter = slideInVertically(
                                initialOffsetY = { -100 },
                                animationSpec = tween(500)
                            )
                        ) {
                            ItemCard(
                                item = item,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        val encodedName = Uri.encode(item.name)
                                        val encodedImage = Uri.encode(item.image)
                                        val encodedColor = Uri.encode(item.color)
                                        navController.navigate("details/${item.id}/$encodedName/$encodedImage/$encodedColor")
                                    }
                                    .padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
