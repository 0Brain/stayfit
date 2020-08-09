
package com.zenith.stayfit.ui.login.view

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
import com.zenith.stayfit.ui.login.model.login.AuthenticationLoginRequest
import com.zenith.stayfit.ui.login.model.login.LoginBody
import com.zenith.stayfit.ui.login.network.AuthenticationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var authenticationService: AuthenticationService
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarTransparent(this@LoginActivity)

        binding.buttonSignIn.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val loginBody = LoginBody(
                username,
                password
            )

            authenticationService.loginUser(
                loginBody
            )
                .enqueue(object : Callback<AuthenticationLoginRequest> {
                    override fun onFailure(
                      call: Call<AuthenticationLoginRequest>,
                      t: Throwable
                    ) {
                        Timber.d(t)
                    }

                    override fun onResponse(
                      call: Call<AuthenticationLoginRequest>,
                      response: Response<AuthenticationLoginRequest>
                    ) {
                            val responseBody = response.body()
                            Toast.makeText(
                                this@LoginActivity,
                                "${responseBody!!}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                })
        }

        binding.buttonSignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            overridePendingTransition(R.anim.slide_right_to_left, R.anim.stay_still)
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
}
