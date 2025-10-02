package com.example.bookify

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

class MenuFragment : Fragment(R.layout.fragment_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.rowAccount).setOnClickListener {
            startActivity(Intent(requireContext(), AccountActivity::class.java))
        }
        view.findViewById<View>(R.id.rowSettings).setOnClickListener {
            Toast.makeText(requireContext(), "Settings tapped", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<View>(R.id.rowAbout).setOnClickListener {
            Toast.makeText(requireContext(), "About us tapped", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<View>(R.id.rowLogout).setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finishAffinity()
        }
    }
}
