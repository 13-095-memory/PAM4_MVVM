package com.memory.pam3_myprofileapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import pam3_myprofileapp.composeapp.generated.resources.Res
import pam3_myprofileapp.composeapp.generated.resources.memory

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    if (uiState.isEditing) {
        EditProfileScreen(
            editName = uiState.editName,
            editBio = uiState.editBio,
            onNameChange = { viewModel.updateEditName(it) },
            onBioChange = { viewModel.updateEditBio(it) },
            onSave = { viewModel.saveProfile() },
            onCancel = { viewModel.cancelEditing() }
        )
    } else {
        ProfileContent(
            uiState = uiState,
            onEditClick = { viewModel.startEditing() },
            onToggleDarkMode = { viewModel.toggleDarkMode() }
        )
    }
}

//Tampilan Profil Utama

@Composable
fun ProfileContent(
    uiState: ProfileUiState,
    onEditClick: () -> Unit,
    onToggleDarkMode: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            DarkModeToggle(
                isDarkMode = uiState.isDarkMode,
                onToggle = onToggleDarkMode
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileHeader(
                name = uiState.name,
                bio = uiState.bio
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = onEditClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit Profile")
            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileInfoCard(
                email = uiState.email,
                phone = uiState.phone,
                location = uiState.location
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Keluar")
            }
        }
    }
}

//Dark Mode Toggle

@Composable
fun DarkModeToggle(
    isDarkMode: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (isDarkMode) "🌙 Dark Mode" else "☀️ Light Mode",
            style = MaterialTheme.typography.bodyMedium
        )
        Switch(
            checked = isDarkMode,
            onCheckedChange = { onToggle() }
        )
    }
}

//Form Edit Profile

@Composable
fun EditProfileScreen(
    editName: String,
    editBio: String,
    onNameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    // Tambahkan Surface di sini juga supaya form edit ikut dark mode
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Edit Profile",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = editName,
                onValueChange = onNameChange,
                label = { Text("Nama") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = editBio,
                onValueChange = onBioChange,
                label = { Text("Bio") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Batal")
            }
        }
    }
}

//Komponen Lain

@Composable
fun ProfileHeader(name: String, bio: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(Res.drawable.memory),
            contentDescription = "Profile Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name, style = MaterialTheme.typography.headlineSmall)
        Text(text = bio, color = Color.Gray)
    }
}

@Composable
fun ProfileInfoCard(email: String, phone: String, location: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            InfoItem("Email", email)
            InfoItem("Phone", phone)
            InfoItem("Location", location)
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label)
        Text(value)
    }
}