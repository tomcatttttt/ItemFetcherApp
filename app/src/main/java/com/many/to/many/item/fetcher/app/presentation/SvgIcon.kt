package com.many.to.many.item.fetcher.app.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun SvgIcon(modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("android.resource://com.many.to.many.item.fetcher.app/raw/refresh_button")
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = "Refresh",

        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(Color.White)
    )
}
