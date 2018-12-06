package com.eihror.coroutines

interface BaseContract {
    interface View {
        fun showMessage(message: String)
    }
}