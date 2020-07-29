package com.zenith.stayfit.ui.login.view.login.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.ActivityLoginBinding
import com.zenith.stayfit.ui.login.model.AuthenticationResponse
import com.zenith.stayfit.ui.login.model.AuthenticationResponseDetails
import com.zenith.stayfit.ui.login.network.LoginService
import com.zenith.stayfit.ui.login.view.forgotpassword.ui.ForgotPasswordActivity
import com.zenith.stayfit.ui.login.view.signup.ui.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject lateinit var loginService: LoginService
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarTransparent(this@LoginActivity)

        binding.buttonSignIn.setOnClickListener {
            loginService.getUser().enqueue(object : Callback<AuthenticationResponse> {
                override fun onFailure(call: Call<AuthenticationResponse>, t: Throwable) {
                    Timber.d(t)
                }

                override fun onResponse(
                    call: Call<AuthenticationResponse>,
                    response: Response<AuthenticationResponse>
                ) {
                    val responseBody = response.body()
                    Toast.makeText(this@LoginActivity, "${responseBody!!}", Toast.LENGTH_LONG).show()
                }

            })
        }

    }

    private fun setStatusBarTransparent(activity: AppCompatActivity) {
        // Make Status bar transparent
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // Make status bar icons color dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.statusBarColor = Color.WHITE
        }
    }

    fun onClick(view: View) {
        if (view.id == R.id.button_signUp) {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            overridePendingTransition(R.anim.slide_right_to_left, R.anim.stay_still)
        } else if (view.id == R.id.button_forgot_password) {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
            overridePendingTransition(R.anim.slide_right_to_left, R.anim.stay_still)
        }
    }
}
