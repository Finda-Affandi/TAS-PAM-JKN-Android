package com.example.project3activity.ui.screens


import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project3activity.R
import com.example.project3activity.contracts.SignUpContract
import com.example.project3activity.presentation.sign_in.SignInState


@Composable
fun LoginForm(
    onSignInAction: (String, String) -> Unit, state: SignInState, onSignInClick: () -> Unit
) {
    val lContext = LocalContext.current

    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    var userId by remember {
        mutableStateOf("")
    }

    var usernameInput by remember {
        mutableStateOf("")
    }

    var passwordInput by remember {
        mutableStateOf("")
    }

    var getUsernameFromSignUp = rememberLauncherForActivityResult(
        contract = SignUpContract(),
        onResult = {username ->
            {usernameInput = username!!}
        }
    )

//    LaunchedEffect(
//        Unit,
//        block = {
//            vm.getUserList()
//        }
//    )

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(colorResource(id = R.color.bg_splash))
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center
//        horizontalAlignment =  Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_form),
                contentDescription = "Main Logo Form",
                modifier = Modifier
                    .padding(top = 162.dp)
                    .size(154.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.welcome_login),
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier
//            .fillMaxSize()
                .padding((20.dp)),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        )
        {
            OutlinedTextField(
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = colorResource(id = R.color.bg_splash),
                    unfocusedBorderColor = Color.Gray,
//                disabledIndicatorColor = Color.Transparent
                ),
//            draw
                trailingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Face,
                            contentDescription = "icon email",
                            )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                value = usernameInput,
                onValueChange = { usernameInput = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_username),
                        style = TextStyle(
                            color = colorResource(id = R.color.bg_splash),
                            fontSize = 16.sp
                        )
                    )
                }

            )
            OutlinedTextField(
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = colorResource(id = R.color.bg_splash),
                    unfocusedBorderColor = Color.Gray,
//                disabledIndicatorColor = Color.Transparent
                ),
//            draw
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.key_form),
                        contentDescription = "Key password"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_password),
                        style = TextStyle(
                            color = colorResource(id = R.color.bg_splash),
                            fontSize = 16.sp
                        )
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
//            modifier = Modifier
//                .fillMaxWidth()

//                .align(alignment = Alignment.CenterHorizontally)
            ) {
//                var auth = false
//                var auth = true
//                for (index in vm.userList) {
//                    if (index.username == usernameInput && index.password == passwordInput) {
//                        auth = true
//                        userId = index.userId.toString()
//                    }
//                }

                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bg_splash)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(58.dp),
//                .padding(25.dp),
//                    .width(12.dp)
                    onClick = {
                        if (usernameInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                            onSignInAction(usernameInput, passwordInput)
                        }
                        else {
                            Toast.makeText(lContext, lContext.getResources().getString(R.string.loginscreen_notfilled), Toast.LENGTH_LONG).show()
                        }
//                        if (auth) {
//                            lContext.startActivity(
//                                Intent(lContext, HomeActivity::class.java)
//                                    .putExtra("userId", userId)
//                            )
//                        }
//                        else {
//                            Toast.makeText(lContext, lContext.getResources().getString(R.string.Wrong_cred), Toast.LENGTH_SHORT).show()
//                        }
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.label_login),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                
                Button(
                    onClick = onSignInClick,
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bg_splash)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(58.dp)) {
                    Image(painter = painterResource(id = R.drawable.google_logo), contentDescription = "google logo", modifier = Modifier.size(52.dp), )
                    Text(text = stringResource(id = R.string.loginscreen_SSO_button), modifier = Modifier.padding(6.dp), style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp), color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(id = R.string.login_prevent),
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedButton(
                    border = BorderStroke(
                        width = 1.dp,
                        color = colorResource(id = R.color.bg_splash)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(52.dp)
                    , onClick = {
//                        lContext.startActivity(
//                            Intent(lContext, SignupActivity::class.java)
//                        )
                        getUsernameFromSignUp.launch("")
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.label_signup),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                
                
            }


        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun LoginFormPreview() {
//    Project3activityTheme() {
//        LoginForm("", "", "", "")
//    }
//}