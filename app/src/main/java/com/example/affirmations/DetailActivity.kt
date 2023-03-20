package com.example.affirmations

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
        val context = LocalContext.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .padding(8.dp)
                .verticalScroll(
                    rememberScrollState()
                )
                .background(color = MaterialTheme.colors.background)
        ) {
            DetailHeader(containerHeight = 400.dp, affirmation = uiState)
            Spacer(modifier = Modifier.padding(15.dp))
            DetailString(affirmation = uiState)
            DetailDescription(affirmation = uiState)
            Spacer(modifier = Modifier.padding(7.5.dp))
            Row(modifier = Modifier.fillMaxWidth(1f).padding(bottom = 5.dp), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { context.sendMail(uiState.mailToString, uiState.mailSubjectString) }, modifier = Modifier.padding(end = 8.dp).background(MaterialTheme.colors.secondary)) {
                    Icon(imageVector = Icons.Filled.Mail, contentDescription = "Mail Button")
                }
                IconButton(onClick = { context.dial(uiState.phoneString) }) { Icon(imageVector = Icons.Filled.Phone, contentDescription = "Call Button", modifier = Modifier.background(MaterialTheme.colors.secondary)) }
            }
        }
    }

    @Composable
    private fun DetailHeader(affirmation: Affirmation, containerHeight: Dp) {
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
    private fun DetailString(affirmation: Affirmation) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)) {
            Text(text = stringResource(id = affirmation.stringResourceId), style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color= MaterialTheme.colors.secondary)
        }
    }

    @Composable
    fun DetailDescription(affirmation: Affirmation) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Divider(modifier = Modifier.padding(bottom = 4.dp))
            Text(text = stringResource(id = affirmation.descriptionResourceId), style = MaterialTheme.typography.body1, overflow = TextOverflow.Visible, textAlign = TextAlign.Justify, color= MaterialTheme.colors.secondary)
        }
    }


    fun Context.sendMail(to: String, subject: String){
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            startActivity(Intent.createChooser(intent, ""))
        }catch (e: ActivityNotFoundException) {} catch (t: Throwable) {}
    }

    fun Context.dial(phone: String){
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        } catch (t: Throwable) {}
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AffirmationsTheme {
            AffirmationDetail(DetailViewModel(11))
        }
    }
}