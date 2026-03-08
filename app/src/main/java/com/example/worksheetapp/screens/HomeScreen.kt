package com.example.worksheetapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
    fun HomeScreen(
    // just stores doesnt know what inside
    onCreateWorkSheetClick: () -> Unit,
    onWorkSheetClick: () -> Unit,
    onLoginClick: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text ("Welcome to WorkHub") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ){ padding ->
        LazyColumn (
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ){
            items()
        }

    }
}