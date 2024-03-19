package com.example.mvx.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.mvx.viewmodel.MyViewModel

@ExperimentalFoundationApi
@Composable
fun MyMainView(viewModel:MyViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3)
        ) {
            items(9) {
                TextButton(
                    enabled = true,
                    onClick = { viewModel.onClickedCellAt(it / 3, it % 3) },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .height(88.dp)
                        .background(
                            Color.Gray
                        )
                        .testTag(it.toString())
                ) {
                    Text(viewModel.cellList[it].value ?: "", fontSize = 25.sp, color = Color.White)
                }
            }
        }

        Text(text = viewModel.winner.value, modifier = Modifier.padding(10.dp), fontSize = 25.sp)
        if (viewModel.winner.value.isNotEmpty()) {
            Text(text = "赢了", fontSize = 25.sp)
        }
    }
}
