package com.example.project3activity.repositories

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import io.grpc.internal.SharedResourceHolder.Resource
//import io.grpc.internal.SharedResourceHolder.Resource
import kotlinx.coroutines.flow.Flow
//import java.util.concurrent.Flow

interface SSORepository {
    
    fun googleSignin(credential: AuthCredential): Flow<Resource<AuthResult>>


}