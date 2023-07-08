package com.example.ecommerceapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.MainActivity
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Author
import com.example.ecommerceapp.databinding.LoginBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class Login : Fragment() {
    private lateinit var binding: LoginBinding
    private lateinit var storedVerificationId: String
    private lateinit var resendToken:
            PhoneAuthProvider.ForceResendingToken
    private lateinit var credential: PhoneAuthCredential


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = LoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetOtp.setOnClickListener {
            initiateOtp()
        }
        binding.btnSignIn.setOnClickListener {

            if (this::storedVerificationId.isInitialized) {
                credential =
                    PhoneAuthProvider.getCredential(
                        storedVerificationId,
                        binding.etOTP.text.toString()
                    )
                signInWithPhoneAuthCredential(credential)
            }
        }


    }

    private fun initiateOtp() {
        val options = PhoneAuthOptions.newBuilder((activity as MainActivity).mAuth)
            .setPhoneNumber("+91" + binding.etPhoneNumber.text.toString())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(context as MainActivity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    Log.d(TAG, "onVerificationCompleted:$credential")
                    signInWithPhoneAuthCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.w(TAG, "onVerificationFailed", e)

                    when (e) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            binding.tvError.text = "Enter correct mobile number"
                        }
                        is FirebaseTooManyRequestsException -> {
                            binding.tvError.text = e.message
                        }
                        is FirebaseAuthMissingActivityForRecaptchaException -> {
                            binding.tvError.text = e.message
                        }
                        else -> {
                            binding.tvError.text = e.message
                        }
                    }
                    binding.tvError.visibility = View.VISIBLE

                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    binding.tvError.visibility = View.GONE
                    Log.d(TAG, "onCodeSent:$verificationId")
                    Toast.makeText(context, "Otp sent", Toast.LENGTH_SHORT).show()
                    storedVerificationId = verificationId
                    resendToken = token
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val mAuth = (activity as MainActivity).mAuth
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(context as MainActivity) { task ->
                if (task.isSuccessful) {
                    binding.tvError.visibility = View.GONE
                    Log.d(TAG, "signInWithCredential:success")
                    val author = Author()
                    author.name = "Trial Author"
                    (activity as MainActivity).viewModel.addAuthor(author, mAuth.uid!!)
                    findNavController().navigate(R.id.action_login_to_home)
                    Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show()
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        binding.tvError.text =
                            (task.exception as FirebaseAuthInvalidCredentialsException).message
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
    }

    companion object {
        private val TAG = Login::class.java.simpleName
    }
}