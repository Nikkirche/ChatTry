package com.example.chattry.ui

import android.content.Intent
import android.os.Bundle
import com.example.chattry.R
import com.example.chattry.databinding.ActivityStartBinding
import com.example.chattry.models.StartModel
import com.example.chattry.view.StartView
import com.example.chattry.presenter.StartPresenter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class StartActivity : MvpAppCompatActivity(R.layout.activity_start), StartView {

    private lateinit var binding: ActivityStartBinding
    @InjectPresenter
    lateinit var presenter: StartPresenter
    @ProvidePresenter
    fun providePresenter():StartPresenter = StartPresenter(StartModel())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.buttonLogIn.setOnClickListener{
            val email = binding.editEmailLogIn.text.toString()
            val password = binding.editPasswordLogIn.text.toString()
            presenter.logIn(email, password)

        }
        binding.buttonRegister.setOnClickListener {
            val name = binding.editNameRegister.text.toString()
            val email = binding.editEmailRegister.text.toString()
            val password = binding.editPasswordRegister.text.toString()
            presenter.register(name, email, password)

        }

    }
    override fun makeUnClickableLogInButton(){
        binding.buttonLogIn.isClickable.not()
    }
    override fun makeClickableLogInButton(){
        binding.buttonLogIn.isClickable
    }
    override fun makeUnClickableRegisterButton(){
        binding.buttonRegister.isClickable.not()
    }
    override fun makeClickableRegisterButton(){
        binding.buttonRegister.isClickable

    }
    override  fun openDialogsActivity(){
        startActivity(Intent(this,DialogsActivity::class.java))
    }



}
