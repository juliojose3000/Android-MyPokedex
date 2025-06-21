package com.loaizasoftware.mypokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //This annotation triggers Hilt's code generation
class MyPokedex: Application() {
}