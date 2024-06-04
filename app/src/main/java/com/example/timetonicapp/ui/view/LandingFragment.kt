package com.example.timetonicapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.timetonicapp.R
import com.example.timetonicapp.ui.viemodel.LandingViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class LandingFragment : Fragment() {

    private lateinit var viewModel: LandingViewModel
    private var ou: String? = null
    private var sesskey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Interceptar el evento de retroceso
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Aquí puedes manejar el evento de retroceso
                // Dejar el cuerpo vacío deshabilitará la navegación hacia atrás
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val toolbarTitle: TextView = view.findViewById(R.id.toolbar_title)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        // Deactivate the default appbar title
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        // Configure the title personalized text
        toolbarTitle.text = "Landing Page"

        // Configure the menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_landing_page, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_logout -> {
                        // Handle logout action
                        showExitDialog()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewModel = ViewModelProvider(this)[LandingViewModel::class.java]

        // Catching the arguments by the safe args from the Login
        arguments?.let {
            val args = LandingFragmentArgs.fromBundle(it)
            //this.sesskey = args.sesskey
            //this.ou = args.ou
            viewModel.loadBooksItems(args.ou, args.sesskey)
        }

        viewModel.books.observe(viewLifecycleOwner) { books ->
            Toast.makeText(context, books.toString(), Toast.LENGTH_LONG).show()
        }

    }

    private fun showExitDialog() {

        MaterialAlertDialogBuilder(requireContext(), R.style.CustomAlertDialog)
            .setTitle("Exit the App")
            .setMessage("Are you sure to exit the application?")
            .setPositiveButton("Accept") { _, _ ->
                // Respond to positive button press
                findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
            }
            .setNegativeButton("No") { dialog, _ ->
                // Respond to negative button press
                dialog.dismiss()
            }.show()
    }
}