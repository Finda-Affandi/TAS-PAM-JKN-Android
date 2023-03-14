package com.example.project3activity.ui.screens


import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project3activity.HomeActivity
import com.example.project3activity.R
import com.example.project3activity.SignupActivity
import com.example.project3activity.contracts.SignUpContracts
import com.example.project3activity.models.UserViewModel
import com.example.project3activity.ui.theme.Project3activityTheme

@Composable
fun LoginForm(vm : UserViewModel) {
    val lContext = LocalContext.current

    var userId by remember {
        mutableStateOf("")
    }

    var usernameInput by remember {
        mutableStateOf("")
    }

    var passwordInput by remember {
        mutableStateOf("")
    }

    LaunchedEffect(
        Unit,
        block = {
            vm.getUserList()
        }
    )

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
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
                        contentDescription = "icon email"
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
                var auth = false
                for (index in vm.userList) {
                    if (index.username == usernameInput && index.password == passwordInput) {
                        auth = true
                        userId = index.userId.toString()
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bg_splash)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(58.dp),
//                .padding(25.dp),
//                    .width(12.dp)
                    onClick = {
                        if (auth) {
                            lContext.startActivity(
                                Intent(lContext, HomeActivity::class.java)
                                    .putExtra("userId", userId)
                            )
                        }
                        else {
                            Toast.makeText(lContext, lContext.getResources().getString(R.string.Wrong_cred), Toast.LENGTH_SHORT).show()
                        }
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
                        lContext.startActivity(
                            Intent(lContext, SignupActivity::class.java)
                        )
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