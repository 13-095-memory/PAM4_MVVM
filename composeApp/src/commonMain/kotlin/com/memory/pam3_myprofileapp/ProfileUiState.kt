package com.memory.pam3_myprofileapp

// Data class = satu sumber kebenaran UI
data class ProfileUiState(
    // Data profil utama
    val name: String = "Memory Simanjuntak",
    val bio: String = "Teknik Informatika",
    val email: String = "memorysimanjuntak@gmail.com",
    val phone: String = "082277586825",
    val location: String = "Lampung",

    // State dark mode
    val isDarkMode: Boolean = false,

    // State edit profile
    val isEditing: Boolean = false,
    val editName: String = "Memory Simanjuntak",  // Nilai sementara saat edit
    val editBio: String = "Teknik Informatika"    // Nilai sementara saat edit
)