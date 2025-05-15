package com.example.native_task_catalift.presentation.screen.interest

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InterestScreen(onContinue: (Context) -> Unit, onExit: () -> Unit?, onBack: () -> Unit,viewModel: InterestViewModel = viewModel()) {

    val query by viewModel.searchQuery.collectAsState()
    val interests by viewModel.filteredInterests.collectAsState()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEEAEA))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(45.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .padding(start = 20.dp, end = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                ,// Track color
        ) { // Progress Indicator
            LinearProgressIndicator(
                progress = 0.7f,
                modifier = Modifier
                    .fillMaxSize(),
                color = Color(0xFF09096E),
                trackColor = Color.Transparent
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text("Your Interests", style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF09096E)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            style = TextStyle(fontSize = 13.5.sp, color = Color.Gray)
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Search Field
        BasicTextField(

            value = query,
            onValueChange = { viewModel.onSearchQueryChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(25.dp))
                .clip(RoundedCornerShape(24.dp))
                .background(color = Color.White),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.width(25.dp).height(25.dp),
                        tint = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(Modifier.weight(1f)) {
                        if (query.isEmpty()) {
                            Text("Search", color = Color.LightGray, fontSize = 16.sp)
                        }
                        innerTextField()
                    }
                }
            },
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Filter Chips
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            interests.forEachIndexed { index, interest ->
                val isSelected = viewModel.selectedValues.contains(interest)
                FilterChip(
                    modifier = Modifier
                        .padding(3.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.White)
                        .clip(RoundedCornerShape(50))
                        ,
                    selected = isSelected,
                    onClick = { viewModel.toggleSelection(interest) },
                    label = { Text(interest, modifier = Modifier.padding(horizontal = 8.dp), fontSize = 13.5.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF09096E),
                        selectedLabelColor = Color.White,
                        containerColor = Color.Transparent,
                        labelColor = Color(0xFF09096E),
                        disabledContainerColor = Color.Gray
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderWidth = 1.dp,
                        borderColor = if (isSelected) Color.Transparent else Color(0xFF09096E),
                        enabled = isSelected,
                        selected = isSelected,

                    ),
                    shape = RoundedCornerShape(50),
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Buttons
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onContinue(context) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF09096E),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Continue")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { onBack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                border = BorderStroke(1.dp, Color(0xFF09096E)),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF09096E)
                )
            ) {
                Text("Back")
            }
        }
    }
}

