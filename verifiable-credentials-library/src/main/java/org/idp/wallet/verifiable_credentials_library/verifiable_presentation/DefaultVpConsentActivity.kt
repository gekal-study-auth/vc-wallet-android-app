package org.idp.wallet.verifiable_credentials_library.verifiable_presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import org.idp.wallet.verifiable_credentials_library.basic.json.JsonUtils
import org.idp.wallet.verifiable_credentials_library.ui.theme.VcWalletTheme
import org.idp.wallet.verifiable_credentials_library.verifiable_credentials.VerifiableCredentialsRecord
import org.idp.wallet.verifiable_credentials_library.verifiable_credentials.VerifiableCredentialsRecords

class DefaultVpConsentActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val viewDataString = intent.getStringExtra("viewData")
    val viewData =
        viewDataString?.let {
          return@let JsonUtils.read(it, VerifiablePresentationViewData::class.java)
        } ?: VerifiablePresentationViewData()
    val recordsString = intent.getStringExtra("verifiableCredentialsRecords")
    val records =
        recordsString?.let {
          return@let JsonUtils.read(it, VerifiableCredentialsRecords::class.java)
        } ?: VerifiableCredentialsRecords()
    val callback = VerifiablePresentationInteractorCallbackProvider.callback
    setContent {
      DefaultVpConsentView(
          viewData = viewData,
          records = records,
          onAccept = { selected ->
            callback.accept(selected)
            finish()
          },
          onReject = {
            callback.reject()
            finish()
          })
    }
    onBackPressedDispatcher.addCallback {
      callback.reject()
      finish()
    }
  }
}

@Preview
@Composable
fun DefaultVpConsentPreView() {
  DefaultVpConsentView(
      viewData = VerifiablePresentationViewData(),
      records =
          VerifiableCredentialsRecords(
              listOf(
                  VerifiableCredentialsRecord("1", "jwt", "", mapOf("key" to "test")),
                  VerifiableCredentialsRecord("2", "jwt", "", mapOf("key" to "test")),
                  VerifiableCredentialsRecord("3", "jwt", "", mapOf("key" to "test")),
                  VerifiableCredentialsRecord("4", "jwt", "", mapOf("key" to "test")),
                  VerifiableCredentialsRecord("5", "jwt", "", mapOf("key" to "test")),
                  VerifiableCredentialsRecord("6", "jwt", "", mapOf("key" to "test")),
                  VerifiableCredentialsRecord("7", "jwt", "", mapOf("key" to "test")))),
      onAccept = { _ -> },
      onReject = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultVpConsentView(
    viewData: VerifiablePresentationViewData,
    records: VerifiableCredentialsRecords,
    onAccept: (List<String>) -> Unit,
    onReject: () -> Unit
) {
  VcWalletTheme {
    Scaffold(
        modifier = Modifier.fillMaxWidth().padding(),
        topBar = {
          Column(modifier = Modifier.padding(Dp(16.0F))) {
            TopAppBar(
                title = { Text(text = "Verifiable Presentation Consent") },
            )
          }
        },
        content = { paddingValue ->
          Column(
              verticalArrangement = Arrangement.SpaceBetween,
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier.padding(paddingValue)) {
                VerifierView(viewData)
                VerifiableCredentialsView(records)
              }
        },
        bottomBar = {
          Row(
              modifier = Modifier.fillMaxWidth().padding(Dp(16.0F)),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
          ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                onClick = { onReject() }) {
                  Text(text = "Reject")
                }
            Button(
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color.White, contentColor = Color.Black),
                border = BorderStroke(Dp(1.0F), Color.Black),
                onClick = { onAccept(listOf("1")) }) {
                  Text(text = "Accept")
                }
          }
        },
    )
  }
}

@Composable
fun VerifierView(viewData: VerifiablePresentationViewData) {
  Column(modifier = Modifier.fillMaxWidth().padding(Dp(16.0F))) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
          //          Image(
          //              painter = rememberAsyncImagePainter(viewData.verifierLogoUri),
          //              contentDescription = null)
          Text(text = viewData.verifierName)
        }
    Row {
      Text(text = "credential")
      Spacer(modifier = Modifier.padding(Dp(8.0F)))
      Text(text = viewData.credentialType)
    }
    Row {
      Text(text = "purpose")
      Spacer(modifier = Modifier.padding(Dp(8.0F)))
      Text(text = viewData.purpose)
    }
  }
}

@Composable
fun VerifiableCredentialsView(verifiableCredentialsRecords: VerifiableCredentialsRecords) {
  val cardList = mutableListOf<Pair<String, String>>()
  verifiableCredentialsRecords.forEach { cardList.add(it.id to JsonUtils.write(it.payload)) }
  LazyColumn(modifier = Modifier.fillMaxWidth()) {
    items(cardList) { (issuer, content) -> VcCardComponent(title = issuer, content = content) }
  }
}

@Composable
fun VcCardComponent(title: String, content: String) {
  Card(modifier = Modifier.fillMaxWidth().padding(Dp(16.0F))) {
    Column(modifier = Modifier.padding(Dp(16.0F))) {
      Text(text = title, style = MaterialTheme.typography.bodyMedium)
      Spacer(modifier = Modifier.height(Dp(8.0F)))
      Text(text = content, style = MaterialTheme.typography.bodySmall)
    }
  }
}
