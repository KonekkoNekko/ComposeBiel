package com.dicoding.submissioncomposebiel.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dicoding.submissioncomposebiel.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Image
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Name Text
        Text(
            text = "Muhammad Nabiel Setiawan",
            modifier = Modifier.fillMaxWidth()
        )

        // Email Text
        Text(
            text = "muhammad.nabiel.20@student.is.ittelkom-sby.ac.id",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
