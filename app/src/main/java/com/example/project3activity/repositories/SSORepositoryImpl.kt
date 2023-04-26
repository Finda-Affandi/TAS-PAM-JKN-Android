package com.example.project3activity.repositories

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import io.grpc.internal.SharedResourceHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SSORepositoryImpl : SSORepository {
    override fun googleSignin(credential: AuthCredential): Flow<SharedResourceHolder.Resource<AuthResult>> {
        return flow {
//            emit(Loading)
        }
    }
}