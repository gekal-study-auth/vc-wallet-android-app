package org.idp.wallet.verifiable_credentials_library.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.idp.wallet.verifiable_credentials_library.VerifiableCredentialsClient
import org.idp.wallet.verifiable_credentials_library.domain.verifiable_credentials.VerifiableCredentialInteractor
import org.idp.wallet.verifiable_credentials_library.domain.verifiable_credentials.VerifiableCredentialsRecords
import org.idp.wallet.verifiable_credentials_library.domain.verifiable_presentation.VerifiablePresentationInteractor
import org.idp.wallet.verifiable_credentials_library.domain.wallet.WalletCredentials
import org.idp.wallet.verifiable_credentials_library.domain.wallet.WalletCredentialsManager
import org.web3j.crypto.Credentials

class VerifiableCredentialsViewModel(private val walletCredentialsManager: WalletCredentialsManager) : ViewModel() {

  private var _vcContent = MutableStateFlow(mapOf<String, VerifiableCredentialsRecords>())
  private var _loading = MutableStateFlow(false)
  val vciState = _vcContent.asStateFlow()
  val loadingState = _loading.asStateFlow()

  fun createCredential(password: String): WalletCredentials {
    val walletCredentials = walletCredentialsManager.create(password)
    return walletCredentials
  }

  fun findCredential(): Credentials? {
    return walletCredentialsManager.find()
  }

  fun deleteCredential() {
    walletCredentialsManager.delete()
  }

  suspend fun requestVcOnPreAuthorization(
      context: Context,
      uri: String,
      format: String,
      interactor: VerifiableCredentialInteractor
  ) {
    try {
      _loading.value = true
      VerifiableCredentialsClient.handlePreAuthorization(context, uri, format, interactor)
    } finally {
      _loading.value = false
    }
  }

  fun getAllCredentials() {
    try {
      _loading.value = true
      val allCredentials = VerifiableCredentialsClient.getAllCredentials()
      _vcContent.value = allCredentials
    } finally {
      _loading.value = false
    }
  }

  suspend fun handleVpRequest(
      context: Context,
      url: String,
      interactor: VerifiablePresentationInteractor
  ) {
    try {
      _loading.value = true
      val result = VerifiableCredentialsClient.handleVpRequest(context, url, interactor)
      println(result)
    } finally {
      _loading.value = false
    }
  }
}
