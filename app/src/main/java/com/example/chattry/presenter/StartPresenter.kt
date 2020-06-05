package com.example.chattry.presenter

import android.util.Log
import com.example.chattry.models.StartModel
import com.example.chattry.view.StartView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import moxy.MvpPresenter
import javax.inject.Inject

class StartPresenter constructor(val startModel: StartModel) : MvpPresenter<StartView>() {
    private lateinit var auth: FirebaseAuth
    val TAG = "StartPresenter"

    override fun onFirstViewAttach() {
        checkStatusOfUser()
    }

    fun register(name: String, email: String, password: String) {
        if(emailIsCorrect(email)&& passwordIsCorrect(password) && nameIsCorrect(name)) {
            viewState.makeUnClickableRegisterButton()

            startModel.userOnFirestoreAdd(name, email).addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                startModel.userOnAuthAdd(email, password).addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        checkStatusOfUser()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        viewState.makeClickableRegisterButton()
                    }
                }.addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                    viewState.makeClickableRegisterButton()

                }

            }
        }
    }

    fun logIn(email: String, password: String) {
        if (emailIsCorrect(email)&& passwordIsCorrect(password)){
                viewState.makeUnClickableLogInButton()
                startModel.userOnAuthLogIn(email, password).addOnSuccessListener { viewState.openDialogsActivity() }
            }
    }

    private fun checkStatusOfUser() {
        if (startModel.getUser() != null) {
            viewState.openDialogsActivity()
        }
    }

    private fun emailIsCorrect(email: String): Boolean {
        return email.length > 7
    }

    private fun passwordIsCorrect(password: String): Boolean {
        return password.length > 5
    }
    private fun nameIsCorrect(name: String): Boolean {
        return  name.length > 4
    }

}