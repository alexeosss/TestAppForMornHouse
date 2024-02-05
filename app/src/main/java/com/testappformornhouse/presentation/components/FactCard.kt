package com.testappformornhouse.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactCard(modifier: Modifier, text: String, click: () -> Unit) {

    Card(
        onClick = {
                  click()
        },
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xB3141413))
    ) {
        Box(modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = Color(0xFFFFF400),
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }

}