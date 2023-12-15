package com.dicoding.submissioncomposebiel.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.submissioncomposebiel.R
import com.dicoding.submissioncomposebiel.model.FakeDataSource
import com.dicoding.submissioncomposebiel.ui.theme.SubmissionComposeBielTheme

@Composable
fun AnimeItem(
    id: Long,
    title: String,
    year: Int,
//    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        val resourceName = "anime_$id"
        val stringIMG = try {
            val resId = R.drawable::class.java.getField(resourceName).getInt(null)
            resId
        } catch (e: Exception) {
            Log.d("Anime Item", "No Image found!")
        }
        Image(
            painter = painterResource(id = stringIMG),
            contentDescription = "Poster anime",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(180.dp)
                .width(120.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(text = title, maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(text = year.toString(),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeItemPreview() {
    SubmissionComposeBielTheme {
        val getData = FakeDataSource.dummyAnimes[1]
        AnimeItem(getData.id, getData.title, getData.year)
    }
}