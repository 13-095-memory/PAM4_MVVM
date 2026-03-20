package com.memory.pam3_myprofileapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    // State internal (private)
    private val _uiState = MutableStateFlow(ProfileUiState())

    // State yang bisa diakses UI (public, read-only)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    //Dark Mode
    fun toggleDarkMode() {
        _uiState.value = _uiState.value.copy(
            isDarkMode = !_uiState.value.isDarkMode
        )
    }

    //Edit Profile

    // Mulai mode edit → salin data asli ke field edit sementara
    fun startEditing() {
        _uiState.value = _uiState.value.copy(
            isEditing = true,
            editName = _uiState.value.name,
            editBio = _uiState.value.bio
        )
    }

    // Update nilai sementara saat user mengetik di TextField
    fun updateEditName(newName: String) {
        _uiState.value = _uiState.value.copy(editName = newName)
    }

    fun updateEditBio(newBio: String) {
        _uiState.value = _uiState.value.copy(editBio = newBio)
    }

    // Simpan → pindahkan nilai edit ke data asli
    fun saveProfile() {
        _uiState.value = _uiState.value.copy(
            name = _uiState.value.editName,
            bio = _uiState.value.editBio,
            isEditing = false
        )
    }

    // Batal edit → kembalikan ke tampilan profil biasa tanpa menyimpan
    fun cancelEditing() {
        _uiState.value = _uiState.value.copy(isEditing = false)
    }
}