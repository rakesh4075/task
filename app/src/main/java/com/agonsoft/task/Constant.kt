package com.agonsoft.task

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.text.Html
import android.text.Spanned

class Constant {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNet = connection.activeNetworkInfo
            if (activeNet != null && activeNet.isConnected) {
                return true
            }
            return false
        }
        fun fromAppHtml(html: String): Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            else
                Html.fromHtml(html)
        }
    }


}