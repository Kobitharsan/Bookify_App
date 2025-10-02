package com.example.bookify

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    private lateinit var group: MaterialButtonToggleGroup
    private lateinit var tabExplore: MaterialButton
    private lateinit var tabFav: MaterialButton
    private lateinit var tabMenu: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Start
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ExploreFragment())
                .commit()
        }

        findViewById<MaterialCardView>(R.id.navContainer)?.let { navCard ->
            ViewCompat.setOnApplyWindowInsetsListener(navCard) { v, insets ->
                val bottomInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
                (v.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    bottomMargin = bottomInset + resources.getDimensionPixelSize(R.dimen.nav_float_margin)
                    v.layoutParams = this
                }
                WindowInsetsCompat.CONSUMED
            }
        }

        group = findViewById(R.id.navGroup)
        tabExplore = findViewById(R.id.tabExplore)
        tabFav = findViewById(R.id.tabFav)
        tabMenu = findViewById(R.id.tabMenu)

        fun selectExplore() {
            setActive(tabExplore, "EXPLORE"); setInactive(tabFav); setInactive(tabMenu)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ExploreFragment())
                .commit()
        }
        fun selectFav() {
            setInactive(tabExplore); setActive(tabFav, "FAVOURITE"); setInactive(tabMenu)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FavouritesFragment())
                .commit()
        }
        fun selectMenu() {
            setInactive(tabExplore); setInactive(tabFav); setActive(tabMenu, "MENU")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MenuFragment())
                .commit()
        }

        group.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            when (checkedId) {
                R.id.tabExplore -> selectExplore()
                R.id.tabFav     -> selectFav()
                R.id.tabMenu    -> selectMenu()
            }
        }

        // Initial label states
        setActive(tabExplore, "EXPLORE")
        setInactive(tabFav)
        setInactive(tabMenu)
    }

    private fun setActive(btn: MaterialButton, label: String) {
        btn.text = label
        btn.isChecked = true
    }

    private fun setInactive(btn: MaterialButton) {
        btn.text = ""
        btn.isChecked = false
    }
}
