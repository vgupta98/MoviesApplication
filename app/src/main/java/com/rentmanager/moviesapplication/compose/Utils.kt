package com.rentmanager.moviesapplication.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.google.accompanist.placeholder.PlaceholderDefaults.shimmerAnimationSpec
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.rentmanager.moviesapplication.retrofit.ApiResult

@Composable
fun ImageFromUrl(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
) {
    val shimmerState = remember { mutableStateOf(false) }
    val mModifier = modifier
        .shimmerPlaceholder(shimmerState.value)


    AsyncImage(
        model = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2$url",
        contentDescription = contentDescription,
        onState = {
            when(it) {
                is AsyncImagePainter.State.Loading -> {
                    shimmerState.value = true
                }
                is AsyncImagePainter.State.Success -> {
                    shimmerState.value = false
                }
                is AsyncImagePainter.State.Error -> {
                    shimmerState.value = false
                }
                else -> {
                    shimmerState.value = false
                }
            }
        },
        modifier = mModifier
    )
}

fun Modifier.shimmerPlaceholder(
    visible: Boolean = true,
    shape: Shape = RectangleShape,
): Modifier = placeholder(
    visible = visible,
    color = Color(0xFF666666),
    shape = shape,
    highlight = PlaceholderHighlight.shimmer(
        highlightColor = Color(0xFF888888),
        animationSpec = shimmerAnimationSpec,
    ),
)

typealias StateAsyncResult<T> = MutableState<ApiResult<T>>

fun <T> stateApiResult(): StateAsyncResult<T> {
    return mutableStateOf(ApiResult())
}