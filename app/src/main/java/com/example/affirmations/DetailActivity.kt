package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme
import com.example.affirmations.viewmodel.DetailViewModel


class DetailActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val affirmationID = DetailViewModel(intent.getIntExtra("affirmationId", 0))
        setContent {
            AffirmationsTheme {
                AffirmationDetail(affirmationID)
            }
        }
    }

    @Composable
    fun AffirmationDetail(detailViewModel: DetailViewModel = viewModel()) {
        val uiState by detailViewModel._uiState.collectAsState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .padding(8.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Image(
                painter = painterResource(id = uiState.imageResourceId),
                contentDescription = "Img",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = stringResource(id = uiState.stringResourceId),
                fontSize = 25.sp,
                fontWeight = FontWeight(800)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = stringResource(id = uiState.descriptionResourceId), textAlign = TextAlign.Justify)
        }
    }

    @Composable
    private fun ProfileHeader(affirmation: Affirmation, containerHeight: Dp) {
        Image(
            modifier = Modifier
                .heightIn(max = containerHeight / 2)
                .fillMaxWidth(),
            painter = painterResource(id = affirmation.imageResourceId),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }

    @Composable
    private fun Title(detailViewModel: DetailViewModel = viewModel()) {
        val uiState by detailViewModel._uiState.collectAsState()
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)) {
            Text(text = stringResource(id = uiState.descriptionResourceId), style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
        }
    }

    @Composable
    fun ProfileProperty(label: String, value: String) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Divider(modifier = Modifier.padding(bottom = 4.dp))
            Text(text = label, modifier = Modifier.height(24.dp), style = MaterialTheme.typography.caption,)
            Text(text = value, modifier = Modifier.height(24.dp), style = MaterialTheme.typography.body1, overflow = TextOverflow.Visible)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AffirmationsTheme {
            AffirmationDetail(DetailViewModel(11))
        }
    }
}