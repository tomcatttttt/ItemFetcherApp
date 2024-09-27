import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.many.to.many.item.fetcher.app.R
import com.many.to.many.item.fetcher.app.presentation.CustomLoader
import com.many.to.many.item.fetcher.app.presentation.ItemViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(viewModel: ItemViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Item Fetcher", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFFF4081),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { viewModel.fetchItems() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.refresh_button),
                            contentDescription = "Refresh",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CustomLoader()
                }

            }
            state.error != null -> {
                Text(text = state.error ?: "Unknown error", modifier = Modifier.fillMaxSize())
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.items) { item ->
                        ItemCard(item)
                    }
                }
            }
        }
    }
}
