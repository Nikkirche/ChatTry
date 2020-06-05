package com.example.chattry.models

import android.content.ContentValues.TAG
import android.util.Log
import com.example.chattry.presenter.StartPresenter
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class StartModel {
    val TAG: String = "StartModel"
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    fun userOnAuthAdd(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }


    fun getUser(): FirebaseUser? {
        auth = Firebase.auth
        Log.d(TAG, "checking AuthStatus...")
        return auth.currentUser
    }

    fun userOnFirestoreAdd(name: String, email: String): Task<DocumentReference> {
        val user = hashMapOf(
            "email" to email,
            "name" to name
        )
        Log.d("auth", "adding UserData")
        return db.collection("users")
            .add(user)
    }
    fun userOnAuthLogIn(email: String,password: String): Task<AuthResult> {
        auth =Firebase.auth
        return auth.signInWithEmailAndPassword(email,password)
    }

}

