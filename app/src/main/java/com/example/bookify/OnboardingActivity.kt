package com.example.bookify

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

data class OnboardingPage(val imageRes: Int, val title: String, val desc: String)

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var indicators: LinearLayout
    private lateinit var btnNext: TextView
    private lateinit var btnSkip: TextView
    private lateinit var btnBack: ImageButton

    private val pages by lazy {
        listOf(
            OnboardingPage(R.drawable.bookscolumn,
                "Read and\nwatch anywhere",
                "You can read & watch at the same time without the internet connection"),
            OnboardingPage(R.drawable.bookself,
                "Personal reading\nplan",
                "Set your reading goals & accept a personalized challenge"),
            OnboardingPage(R.drawable.property,
                "Choose the correct\nanswer",
                "We read the best books, highlight key ideas and insights, create summaries and visual narratives for you")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        indicators = findViewById(R.id.indicators)
        btnNext = findViewById(R.id.btnNext)
        btnSkip = findViewById(R.id.btnSkip)
        btnBack = findViewById(R.id.btnBack)

        viewPager.adapter = OnboardingAdapter(pages)
        setupIndicators(pages.size)
        setCurrentIndicator(0)
        updateButtons(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)
                updateButtons(position)
            }
        })

        btnNext.setOnClickListener {
            val i = viewPager.currentItem
            if (i + 1 < pages.size) viewPager.currentItem = i + 1
            else openMain()
        }
        btnSkip.setOnClickListener { openMain() }
        btnBack.setOnClickListener {
            val i = viewPager.currentItem
            if (i > 0) viewPager.currentItem = i - 1
        }
    }

    private fun openMain() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun setupIndicators(count: Int) {
        indicators.removeAllViews()
        repeat(count) {
            val dot = View(this).apply {
                val size = resources.getDimensionPixelSize(R.dimen.dot_size)
                layoutParams = LinearLayout.LayoutParams(size, size).apply {
                    marginStart = resources.getDimensionPixelSize(R.dimen.dot_gap)
                    marginEnd = resources.getDimensionPixelSize(R.dimen.dot_gap)
                }
                background = ContextCompat.getDrawable(this@OnboardingActivity, R.drawable.dot_inactive)
            }
            indicators.addView(dot)
        }
    }

    private fun setCurrentIndicator(index: Int) {
        for (i in 0 until indicators.childCount) {
            val dot = indicators.getChildAt(i)
            dot.background = ContextCompat.getDrawable(
                this, if (i == index) R.drawable.dot_active else R.drawable.dot_inactive
            )
        }
    }

    private fun updateButtons(position: Int) {
        btnBack.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
        btnNext.text = if (position == pages.lastIndex) "Get Started" else "Next"
    }
}
