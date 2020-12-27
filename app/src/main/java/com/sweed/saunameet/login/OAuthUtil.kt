package com.sweed.saunameet.login

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log

class OAuthUtil (context : Context) {



    val am: AccountManager = AccountManager.get(context)
    val options = Bundle()


    init {

        val accounts: Array<out Account> = am.getAccountsByType("com.google")

        for (account in accounts){
            Log.i("Accounts", "${account.toString()}")
        }


//

//        am.getAuthToken(
//            myAccount_,                     // Account retrieved using getAccountsByType()
//            "Manage your tasks",            // Auth scope
//            options,                        // Authenticator-specific options
//            this,                           // Your activity
//            OnTokenAcquired(),              // Callback called when a token is successfully acquired
//            Handler(OnError())              // Callback called if an error occurs
//        )


    }

    private class OnTokenAcquired : AccountManagerCallback<Bundle> {

        override fun run(result: AccountManagerFuture<Bundle>) {
            // Get the result of the operation from the AccountManagerFuture.
            val bundle: Bundle = result.getResult()

            // The token is a named value in the bundle. The name of the value
            // is stored in the constant AccountManager.KEY_AUTHTOKEN.
            val token: String = bundle.getString(AccountManager.KEY_AUTHTOKEN)?: ""
        }
    }



}
