/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme
import com.example.affirmations.ui.theme.Typography
import com.example.affirmations.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      //commit
      AffirmationApp()
    }
  }
}

@Composable
fun AffirmationApp(_viewModel: MainViewModel = viewModel()) {
  val uiState by _viewModel._uiState.collectAsState()
  AffirmationsTheme {
    val count = rememberSaveable { mutableStateOf(0) }
    AffirmationList(affirmationList = uiState, modifier = Modifier.background(color = MaterialTheme.colors.background))
  }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
  LazyColumn {
    items(affirmationList) { affirmation ->
      AffirmationCard(affirmation)
    }
  }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
  // TODO 1. Your card UI
  var expanded by remember { mutableStateOf(false) }
  Card(modifier=modifier,border = BorderStroke(
    2.dp, color= MaterialTheme.colors.secondary), elevation = 2.dp) {
    Column(modifier = Modifier
      .animateContentSize(
        animationSpec = spring(
          dampingRatio = Spring.DampingRatioMediumBouncy,
          stiffness = Spring.StiffnessLow
        )
      )) {
      Row (modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        CardImage(affirmation)
        Text(text = stringResource(affirmation.stringResourceId), style = typography.h6, color = MaterialTheme.colors.secondary,
          modifier = Modifier
            .weight(1.5f)
            .padding(10.dp))
        ItemButton(
          expanded = expanded,
          onClick = { expanded = !expanded},
        )
      }
      if (expanded) {
        stringResource(affirmation.descriptionResourceId)
        affirmation.id
      }
    }
  }
}

@Composable
private fun CardImage(affirmation: Affirmation) {
  Image(
    painter = painterResource(id = affirmation.imageResourceId),
    contentDescription = null,
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .padding(7.dp)
      .size(84.dp)
      .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
  )
}

@Composable
fun ItemButton(expanded: Boolean,
               onClick: () -> Unit,
               modifier: Modifier = Modifier){
  IconButton(onClick = onClick) {
    Icon(
      imageVector = if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropDown,
      tint = MaterialTheme.colors.secondary,
      contentDescription = "ItemButton"
    )
  }
}

@Composable
fun Description(affirmationId: Int, description: String, modifier: Modifier = Modifier) {
  Column(
    modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 8.dp, end = 4.dp)
  ) {
    Text(text = "Description: ", style= Typography.body1, color = MaterialTheme.colors.primary, modifier = Modifier.padding(2.dp))
    Text(text = description,
      style= Typography.body2,
      color = MaterialTheme.colors.secondary,
      modifier = Modifier.padding(2.dp),
      overflow = TextOverflow.Ellipsis,
      maxLines = 2)
    val affirmationContxt = LocalContext.current
    Row(Modifier.clickable {
      affirmationContxt.startActivity(
        Intent(
          affirmationContxt, DetailActivity::class.java
        ).putExtra("affirmationId", affirmationId)
      )
    }) {
      Text(text = stringResource(R.string.detailButton), style= Typography.body1, color = MaterialTheme.colors.primary, modifier = Modifier.padding(start = 310.dp, top = 10.dp, bottom = 8.dp, end = 4.dp))
    }
  }
}


@Preview
@Composable
private fun AffirmationCardPreview() {
  AffirmationCard (Affirmation(11, R.string.affirmation1, R.string.description1, R.drawable.image1))
}
