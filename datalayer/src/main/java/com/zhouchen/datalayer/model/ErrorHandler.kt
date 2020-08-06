package com.zhouchen.datalayer.model

import android.content.Context
import android.view.View
import android.widget.Toast

object ErrorHandler {

    private const val NETWORK_ERROR_MESSAGE =
        "Please check your internet connectivity and try again!"
    private const val EMPTY_RESPONSE = "Server returned empty response."
    const val NO_SUCH_DATA = "Data not found in the database"
    const val UNKNOWN_ERROR = "An unknown error occurred!"

    fun handleError(
        view: View,
        throwable: Error,
        shouldToast: Boolean = false,
        refreshAction: () -> Unit = {}
    ) {
        if (shouldToast) {
            showLongToast(
                view.context,
                throwable.message
            )
        }
    }

    private fun showLongToast(context: Context, message: String) = Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()

}