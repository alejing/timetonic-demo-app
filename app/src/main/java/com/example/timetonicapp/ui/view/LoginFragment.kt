package com.example.timetonicapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.timetonicapp.R
import com.example.timetonicapp.ui.viemodel.LoginViewModel
import com.example.timetonicapp.utils.isNetworkAvailable
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Intercept the back button event
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Here you can handle the back event
                // Leaving the body empty will disable back navigation
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton: Button = view.findViewById(R.id.login_button)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        viewModel.setAuthCallback(object : LoginViewModel.AuthCallback {

            override fun onAuthSuccess(message: String, ou: String, sesskey: String) {
                // Go to LandingFragment
                progressBar.visibility = View.GONE
                //Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                val action =
                LoginFragmentDirections.actionLoginFragmentToLandingFragment(
                    sesskey,
                    ou
                )
                findNavController().navigate(action)
            }

            override fun onAuthError(error: String) {
                // Handle authentication error
                progressBar.visibility = View.GONE
                //Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                showErrorDialog(error)
            }
        })

        loginButton.setOnClickListener {

            progressBar.visibility = View.VISIBLE

            // Check if there is an internet connection
            if (!isNetworkAvailable(this.requireContext())) {
                showErrorDialog("No internet connection")
                // Hide the progress bar when something went wrong
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }
            // Start the authentication process
            viewModel.startAuthentication("android.developer@timetonic.com", "Android.developer1")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setAuthCallback(null)  // Prevent memory leaks
    }

    private fun showErrorDialog(message: String?) {

        MaterialAlertDialogBuilder(requireContext(), R.style.CustomAlertDialog)
            .setTitle("Oops, something went wrong")
            .setMessage(message)
            .setPositiveButton("I understand")  { dialog, _ ->
                // Respond to positive button press
                dialog.dismiss()
            }
            .show()

    }

}