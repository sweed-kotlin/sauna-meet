package com.sweed.saunameet.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.sweed.saunameet.MainActivity
import com.sweed.saunameet.R


//  Refrence>
//  https://developers.google.com/identity/sign-in/android/start-integrating
//  https://developer.android.com/training/id-auth
//  User-Data & Auto-Fill
//  https://developer.android.com/guide/user-data

//  TODO
//  Migrate Navigation, Merge Activity Nav graphes>
//  https://developer.android.com/guide/navigation/navigation-migrate#add

const val RC_SIGN_IN = 200

const val DISPLAY_NAME_KEY = "DISPLAY_NAME_KEY"

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var oAuth: OAuthUtil
    private lateinit var mGoogleApiClient: GoogleSignInClient

    private lateinit var username_display_text: TextView
    private lateinit var googleSignInButton: SignInButton
    private lateinit var sign_out_button_g: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_google)

        googleSignInButton = findViewById<SignInButton>(R.id.sign_in_button_g)
        username_display_text = findViewById<TextView>(R.id.username_display_text)
        sign_out_button_g = findViewById<Button>(R.id.sign_out_button_g)

        googleSignInButton.setSize(SignInButton.SIZE_ICON_ONLY)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        return super.onCreateView(name, context, attrs)
    }


    override fun onStart() {
        super.onStart()

        sign_out_button_g.setOnClickListener {
            signOut();

        }

//      Check for existing Google Sign In account, if the user is already signed in
//      the GoogleSignInAccount will be non-null.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().build()

        mGoogleApiClient = GoogleSignIn.getClient(this, gso)
        if (mGoogleApiClient == null) {
//          Update your UI to display the Google Sign-in button.
//          updateUI(account)
//            username_editText.setText("", TextView.BufferType.EDITABLE)
            username_display_text.text = ""
            username_display_text.visibility = View.GONE
            googleSignInButton.visibility = View.VISIBLE

        } else {
            googleSignInButton.setOnClickListener {
                val signInIntent = mGoogleApiClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
            val acct = GoogleSignIn.getLastSignedInAccount(this)
            if (acct != null) {
                val personName = acct.displayName
                val personGivenName = acct.givenName
                val personFamilyName = acct.familyName
                val personEmail = acct.email
                val personId = acct.id
                val personPhoto: Uri? = acct.photoUrl

//          TODO Refactor ?: ElvisOperator
                showLogInView(acct.displayName ?: "")
            }
//          GoogleSignInAccount --> user has already signed-in
//          launch your main activity, or whatever is appropriate for your app
        }

    }

    private fun signOut() {
        mGoogleApiClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void?> {
                showLogOutView()
            })
    }

    //  TODO Revoke App Access to Google API
    private fun revokeAccess() {
        mGoogleApiClient.revokeAccess()
            .addOnCompleteListener(this, OnCompleteListener<Void?> {
                // ...
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

//          TODO Refactor ?: ElvisOperator
            var displayName = account?.displayName ?: ""
            if (!displayName.isNullOrEmpty()) {
                showLogInView(displayName)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(DISPLAY_NAME_KEY, displayName)
                startActivity(intent)
            }


            // Signed in successfully, show authenticated UI.
//            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("handleSignInResult", "signInResult:failed code=" + e.statusCode)

            showLogOutView()

//            updateUI(null)
        }
    }

    private fun showLogInView(loginText: String) {
        username_display_text.text = loginText
        username_display_text.visibility = View.VISIBLE
        sign_out_button_g.visibility = View.VISIBLE
        googleSignInButton.visibility = View.GONE

    }

    private fun showLogOutView() {
        username_display_text.text = ""
        username_display_text.visibility = View.GONE
        googleSignInButton.visibility = View.VISIBLE
        sign_out_button_g.visibility = View.GONE
    }

    //    prevent back-action
    override fun onBackPressed() {
        // super.onBackPressed();
        return
    }

}