package com.testappformornhouse.presentation.screens.main

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.testappformornhouse.R
import com.testappformornhouse.presentation.components.ButtonComponent
import com.testappformornhouse.presentation.components.FactCard
import com.testappformornhouse.presentation.navigation.NavigationTree
import java.net.URLEncoder

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(navController: NavHostController, vm: MainViewModel = hiltViewModel()) {

    val uiState by vm.uiState.collectAsState()

    val pattern = remember { Regex("^\\d+\$") }

    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = Unit, block = {
        vm.updateDatabase()
    })

    Column(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = uiState.number,
                onValueChange = {
                    if (it.isEmpty() || it.matches(pattern)) {
                        vm.obtainEvent(MainEvent.ChangeNumber(it))
                    }
                },
                modifier = Modifier.size(200.dp, 60.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    autoCorrect = false
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() },
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enter_number),
                        color = Color(0xB3000000)
                    )
                }
            )
            ButtonComponent(modifier = Modifier, text = stringResource(id = R.string.get_fact)) {
                if (uiState.number.isNotEmpty()) {
                    keyboardController?.hide()
                    navController.navigate(NavigationTree.FactScreen.name + "/${uiState.number}")
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.please_enter_number),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            ButtonComponent(
                modifier = Modifier.clickable { },
                text = stringResource(id = R.string.get_fact_random)
            ) {
                navController.navigate(NavigationTree.FactScreen.name + "/null")
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.history),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )
            LazyColumn(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                if (uiState.listOfFacts.isEmpty()) {
                    item {
                        CircularProgressIndicator(
                            color = Color(0xFF000000),
                            modifier = Modifier.size(50.dp)
                        )
                    }
                } else {
                    items(uiState.listOfFacts) {
                        FactCard(modifier = Modifier, text = it.fact) {
                            navController.navigate(
                                NavigationTree.FactScreen.name + "/${
                                    URLEncoder.encode(
                                        it.fact,
                                        "UTF-8"
                                    )
                                }"
                            )
                        }
                    }
                }


            }
        }

    }
}


