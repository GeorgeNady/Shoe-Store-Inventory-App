/*
 * Created by Georg on 7/16/22, 4:10 AM
 * Copyright (c) 2022.  All rights reserved.
 * Last modified 7/16/22, 4:00 AM
 */

package com.udacity.shoestore.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine


/**
 * # how to Using Flows for Form Validation in Android:
 * ### [Using Flows for Form Validation in Android](https://levelup.gitconnected.com/using-flows-for-form-validation-in-android-79016b00c079).
 * ---
 * # you can also learn how Substituting LiveData with the newer SharedFlow & StateFlow
 * ### [Substituting LiveData with the newer SharedFlow & StateFlow](https://medium.com/m/global-identity?redirectUrl=https%3A%2F%2Fproandroiddev.com%2Fshould-we-choose-kotlins-stateflow-or-sharedflow-to-substitute-for-android-s-livedata-2d69f2bd6fa5)
 */
class LoginViewModel : ViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    val isSubmitEnabled: Flow<Boolean> = combine(email, password) { email, password ->
        val regexString = "[a-zA-Z]+"
        val isNameCorrect = email.matches(regexString.toRegex())
        val isPasswordCorrect = password.length > 8
        return@combine isNameCorrect and isPasswordCorrect
    }

}