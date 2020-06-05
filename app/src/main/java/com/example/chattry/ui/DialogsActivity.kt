package com.example.chattry.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chattry.R
import com.example.chattry.databinding.ActivityDialogsBinding
import com.example.chattry.databinding.ActivityStartBinding
import com.example.chattry.presenter.DialogsPresenter
import com.example.chattry.presenter.StartPresenter
import com.example.chattry.view.DialogsView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class DialogsActivity : MvpAppCompatActivity(R.layout.activity_dialogs),DialogsView {
    private lateinit var binding: ActivityDialogsBinding
    @InjectPresenter
    lateinit var presenter: DialogsPresenter
    @ProvidePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dialogs)
    }
}
