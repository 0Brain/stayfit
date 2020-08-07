package com.zenith.stayfit.ui.login.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.zenith.stayfit.R
import com.zenith.stayfit.commons.ServerConstants.failedToConnect
import com.zenith.stayfit.commons.ServerConstants.successfulSignUpResponse
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

    private var parentJob: Job = Job()
    private var exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
       Timber.d(exception)
    }
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob + exceptionHandler)


    private lateinit var binding: ActivitySignupBinding

    //Hilt ViewModel Injection
    private val signUpViewModel: SignUpViewModel by viewModels()

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
                parentJob = coroutineScope.launch {
                    getServerMessage().let { apiResponse ->
                        Snackbar.make(binding.root, apiResponse, Snackbar.LENGTH_SHORT).show()
                        if (apiResponse == successfulSignUpResponse) {
                            clearForm(binding.root)
                        }
                    }
                }
                parentJob.invokeOnCompletion {throwable ->
                    throwable!!.message.let {
                        Timber.d(throwable)
                        Snackbar.make(binding.root, failedToConnect, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private suspend fun getServerMessage(): String = withContext(Dispatchers.IO) {
        signUpViewModel.signUpEntries(
            binding.etUsername.text.toString(),
            binding.etEmail.text.toString(),
            binding.etPhone.text.toString().toLong(),
            binding.etPassword.text.toString()
        )
    }

    private fun setStatusBarWhite(activity: AppCompatActivity) {
        // Make status bar icons color dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.statusBarColor = Color.WHITE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.stay_still, R.anim.slide_left_to_right);
    }

    private fun validateFields(): Boolean {
        if (binding.etPassword.text!!.isEmpty() || binding.etConfirmPassword.text!!.isEmpty() || binding.etEmail.text!!.isEmpty() || binding.etUsername.text!!.isEmpty() || binding.etPhone.text!!.isEmpty()) {
            Snackbar.make(binding.root, "Please fill all fields", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (!checkEmail(binding.etEmail.text.toString())) {
            Snackbar.make(binding.root, "Invalid Email", Snackbar.LENGTH_SHORT).show()
            return false
        } else if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
            Snackbar.make(binding.root, "Passwords do not match", Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}