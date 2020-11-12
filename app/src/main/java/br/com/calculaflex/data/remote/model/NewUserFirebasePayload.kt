package br.com.calculaflex.data.remote.model

import com.google.firebase.firestore.Exclude

data class NewUserFirebasePayload(
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    @get:Exclude val password: String? = null
)