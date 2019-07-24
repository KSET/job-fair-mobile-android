package com.undabot.jobfair.account.view.model

import android.graphics.Bitmap

sealed class AccountViewModel {
    data class Student(val name: String, val qrCode: Bitmap?): AccountViewModel()
    data class Company(val name: String): AccountViewModel()
}