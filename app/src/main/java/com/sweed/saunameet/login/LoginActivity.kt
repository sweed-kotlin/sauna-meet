package com.sweed.saunameet.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.sweed.saunameet.R


//  TODO
//  Migrate Navigation, Merge Activity Nav graphes>
//  https://developer.android.com/guide/navigation/navigation-migrate#add

const val RC_SIGN_IN = 200

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var oAuth: OAuthUtil

    private lateinit var username_editText: EditText
    private lateinit var googleSignInButton: SignInButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_google)

        googleSignInButton = findViewById<SignInButton>(R.id.sign_in_button_g)
        username_editText = findViewById<EditText>(R.id.username_g_text)

        googleSignInButton.setSize(SignInButton.SIZE_ICON_ONLY)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        return super.onCreateView(name, context, attrs)
    }


    override fun onStart() {
        super.onStart()


//      Check for existing Google Sign In account, if the user is already signed in
//      the GoogleSignInAccount will be non-null.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().build()

        val mGoogleApiClient = GoogleSignIn.getClient(this, gso)
        if (mGoogleApiClient == null) {
//          Update your UI to display the Google Sign-in button.
//          updateUI(account)
            username_editText.setText("", TextView.BufferType.EDITABLE)
            googleSignInButton.visibility = View.VISIBLE

        } else {
            findViewById<SignInButton>(R.id.sign_in_button_g).setOnClickListener {
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

                username_editText.setText(acct.displayName, TextView.BufferType.EDITABLE)
                googleSignInButton.visibility = View.GONE
            }
//          GoogleSignInAccount --> user has already signed-in
//          launch your main activity, or whatever is appropriate for your app
        }

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


            username_editText.setText(account?.displayName, TextView.BufferType.EDITABLE)
            googleSignInButton.visibility = View.GONE


            // Signed in successfully, show authenticated UI.
//            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("handleSignInResult", "signInResult:failed code=" + e.statusCode)

            username_editText.setText("", TextView.BufferType.EDITABLE)
            googleSignInButton.visibility = View.VISIBLE

//            updateUI(null)
        }
    }


}