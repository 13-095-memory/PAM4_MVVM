package com.memory.pam3_myprofileapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun App() {
    // ViewModel dibuat di sini supaya bisa dipakai App & ProfileScreen
    val viewModel: ProfileViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value

    // Pilih color scheme berdasarkan state isDarkMode
    MaterialTheme(
        colorScheme = if (uiState.isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        ProfileScreen(viewModel = viewModel)
    }
}