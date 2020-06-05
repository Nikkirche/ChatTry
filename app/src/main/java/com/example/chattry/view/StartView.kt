package com.example.chattry.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
@StateStrategyType(AddToEndSingleStrategy::class)
interface StartView: MvpView {
    fun makeUnClickableLogInButton()
    fun makeClickableLogInButton()
    fun makeUnClickableRegisterButton()
    fun makeClickableRegisterButton()
    fun openDialogsActivity()
}
