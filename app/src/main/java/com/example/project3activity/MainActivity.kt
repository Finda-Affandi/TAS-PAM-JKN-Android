package com.example.project3activity

//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project3activity.presentation.sign_in.GoogleAuthUiClient
import com.example.project3activity.presentation.sign_in.ProfileScreen
import com.example.project3activity.presentation.sign_in.SignInViewModel
import com.example.project3activity.ui.screens.LoginForm
import com.example.project3activity.ui.theme.Project3activityTheme
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)

        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val lcontext = LocalContext.current
                    val navController = rememberNavController()
                    NavHost(navController = navController , startDestination = "sign_in") {
                        composable("sign_in") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthUiClient.getSignedInUser() != null) {
//                                    navController.navigate("profile")
                                    lcontext.startActivity(Intent(lcontext, HomeActivity::class.java))
                                }
                            }

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    Toast.makeText(lcontext, "Sign In Sucessful", Toast.LENGTH_LONG)
                                        .show()
                                    lcontext.startActivity(Intent(lcontext, HomeActivity::class.java))
//                                    navController.navigate("profile")
                                    viewModel.resetState()
                                }
                            }

                            LoginForm(onSignInAction = ::doAuth, state = state, onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                            )

//                            SignInScreen(state = state, onSignInCLick = {
//                                lifecycleScope.launch {
//                                    val signInIntentSender =  googleAuthUiClient.signIn()
//                                    launcher.launch(
//                                        IntentSenderRequest.Builder(
//                                            signInIntentSender ?: return@launch
//                                        ).build()
//                                    )
//                                }
//                            }

                        }

                        composable("profile") {
                            ProfileScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            lcontext,
                                            "Successfully Signed Out",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        navController.popBackStack()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun doAuth(username: String, password: String) {

        val authErrors = mapOf("ERROR_INVALID_CUSTOM_TOKEN" to R.string.error_login_custom_token,
            "ERROR_CUSTOM_TOKEN_MISMATCH" to R.string.error_login_custom_token_mismatch,
            "ERROR_INVALID_CREDENTIAL" to R.string.error_login_credential_malformed_or_expired,
            "ERROR_INVALID_EMAIL" to R.string.error_login_invalid_email,
            "ERROR_WRONG_PASSWORD" to R.string.error_login_wrong_password,
            "ERROR_USER_MISMATCH" to R.string.error_login_user_mismatch,
            "ERROR_REQUIRES_RECENT_LOGIN" to R.string.error_login_requires_recent_login,
            "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" to R.string.error_login_accounts_exits_with_different_credential,
            "ERROR_EMAIL_ALREADY_IN_USE" to  R.string.error_login_email_already_in_use,
            "ERROR_CREDENTIAL_ALREADY_IN_USE" to R.string.error_login_credential_already_in_use,
            "ERROR_USER_DISABLED" to R.string.error_login_user_disabled,
            "ERROR_USER_TOKEN_EXPIRED" to R.string.error_login_user_token_expired,
            "ERROR_USER_NOT_FOUND" to R.string.error_login_user_not_found,
            "ERROR_INVALID_USER_TOKEN" to R.string.error_login_invalid_user_token,
            "ERROR_OPERATION_NOT_ALLOWED" to R.string.error_login_operation_not_allowed,
            "ERROR_WEAK_PASSWORD" to R.string.error_login_password_is_weak)


        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(
                        Intent(this, HomeActivity::class.java)
                            .putExtra("username", username)
                    )
                    finish()
                }
//                else{
//                    val exception = task.exception
//                    if (exception is FirebaseAuthInvalidCredentialsException) {
//                        Toast.makeText(this, "Invalid credentials. Please check your email and password.", Toast.LENGTH_LONG).show()
//                    } else if (exception is FirebaseAuthInvalidUserException) {
//                        when (exception.errorCode) {
//                            "ERROR_USER_NOT_FOUND" -> Toast.makeText(this, "User not found. Please check your email address.", Toast.LENGTH_LONG).show()
//                            "ERROR_USER_DISABLED" -> Toast.makeText(this, "User account is disabled. Please contact support.", Toast.LENGTH_LONG).show()
//                            else -> Toast.makeText(this, "Unknown error. Please try again.", Toast.LENGTH_LONG).show()
//                        }
//                    } else {
//                        Toast.makeText(this, "Authentication failed: ${exception?.message}", Toast.LENGTH_LONG).show()
//                    }
//
//                }
            }
            .addOnFailureListener {
                val errorCode = (it as FirebaseAuthException).errorCode
                val errorMessage = authErrors[errorCode] ?: R.string.error_login_default_error
                Toast.makeText(this, this.getString(errorMessage),Toast.LENGTH_LONG).show()
            }

    }


}

