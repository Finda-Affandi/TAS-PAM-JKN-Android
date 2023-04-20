package com.example.project3activity

//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.project3activity.models.UserViewModel
import com.example.project3activity.ui.screens.LoginForm
import com.example.project3activity.ui.screens.navigation
import com.example.project3activity.ui.theme.Project3activityTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        val vm = UserViewModel()
        super.onCreate(savedInstanceState)

        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginForm(onSignInAction = ::doAuth)
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

