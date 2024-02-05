package com.testappformornhouse.presentation.screens.fact

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.testappformornhouse.R
import com.testappformornhouse.presentation.components.FactCard
import com.testappformornhouse.presentation.components.FactCardForSecondScreen
import com.testappformornhouse.presentation.navigation.NavigationTree

@Composable
fun FastScreen(navController: NavHostController, viewModel: FactViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.number.isEmpty()) {
                CircularProgressIndicator(
                    color = Color(0xFF000000),
                    modifier = Modifier.size(50.dp)
                )
            } else {
                FactCardForSecondScreen(modifier = Modifier.size(200.dp, 150.dp), text = uiState.number)

                FactCardForSecondScreen(modifier = Modifier.height(300.dp), text = uiState.factAboutNumber)

            }


        }

        Icon(imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            tint = Color(0xFFFFF400),
            modifier = Modifier
                .padding(start = 24.dp, top = 24.dp)
                .align(Alignment.TopStart)
                .clickable {
                    navController.popBackStack(route = NavigationTree.MainScreen.name, inclusive = false)
                })


    }


}