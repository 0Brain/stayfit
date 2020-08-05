package com.zenith.stayfit.ui.login.view

import android.app.AlertDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.zenith.stayfit.R
import com.zenith.stayfit.commons.checkEmail
import com.zenith.stayfit.commons.clearForm
import com.zenith.stayfit.databinding.ActivitySignupBinding
import com.zenith.stayfit.ui.login.network.AuthenticationService
import com.zenith.stayfit.ui.login.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    companion object {
        private val parentJob = Job()
        val coroutineScope = CoroutineScope(Dispatchers.IO + parentJob)
    }

    private lateinit var binding: ActivitySignupBinding

    //Hilt ViewModel Injection
    private val noteViewModel: SignUpViewModel by viewModels()
    private lateinit var alertDialog: AlertDialog

    @Inject
    lateinit var authenticationService: AuthenticationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        setStatusBarWhite(this@SignUpActivity)



        binding.buttonSignin.setOnClickListener {
            if (validateFields()) {
                val responseMessage: String
                runBlocking {
                    responseMessage =
                        withContext(coroutineScope.coroutineContext) {
                            Timber.d("Main ${Thread.currentThread().name}")
                            noteViewModel.signUpEntries(
                                binding.etUsername.text.toString(),
                                binding.etEmail.text.toString(),
                                binding.etPhone.text.toString().toLong(),
                                binding.etPassword.text.toString()
                            )
                        }
                }
                Snackbar.make(binding.root,responseMessage,Snackbar.LENGTH_SHORT).show()
            }
            clearForm(binding.root)
        }
    }

    private fun setStatusBarWhite(activity: AppCompatActivity) {
        // Make status bar icons color dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.statusBarColor = Color.WHITE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.stay_still, R.anim.slide_left_to_right);
    }

    private fun validateFields(): Boolean {
        if (binding.etPassword.text!!.isEmpty() && binding.etConfirmPassword.text!!.isEmpty() && binding.etEmail.text!!.isEmpty() && binding.etUsername.text!!.isEmpty() && binding.etPhone.text!!.isEmpty()) {
            Toast.makeText(
                this@SignUpActivity,
                "Please fill all fields",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (!checkEmail(binding.etEmail.text.toString())) {
            Toast.makeText(
                this@SignUpActivity,
                "Invalid Email",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
            Toast.makeText(
                this@SignUpActivity,
                "Passwords do not match",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}