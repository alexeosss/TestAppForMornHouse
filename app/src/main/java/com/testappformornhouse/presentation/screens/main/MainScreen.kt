package com.testappformornhouse.presentation.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.testappformornhouse.R
import com.testappformornhouse.presentation.components.ButtonComponent

@Composable
fun MainScreen(navController: NavHostController, vm: MainViewModel = hiltViewModel()) {

    val pattern = remember { Regex("^\\d+\$") }

    Column(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = "12",
                onValueChange = {
                    if (it.isEmpty() || it.matches(pattern)) {
                        if (it.length <= 5){
//                            obtainEvent(MultiplayerEvent.OnCodeChange(it))
                        }
                    }
                },
                modifier = Modifier.size(200.dp, 60.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    autoCorrect = false
                ),
                keyboardActions = KeyboardActions(
                    onDone = { },
                ),
                singleLine = true
            )
            ButtonComponent(modifier = Modifier, text = stringResource(id = R.string.get_fact)) {

            }
            ButtonComponent(
                modifier = Modifier.clickable { },
                text = stringResource(id = R.string.get_fact_random)
            ) {

            }
        }
        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {

        }
    }
}


