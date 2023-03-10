package com.example.layoutmake

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.layoutmake.databinding.ActivitySettingsBinding


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        binding = ActivitySettingsBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.arrowBackButton.setOnClickListener {
            finish()
        }

        binding.shareTextView.setOnClickListener { createShareIntent() }

        binding.supportTextView.setOnClickListener {
            createMessageIntent()
        }

        binding.agreementTextView.setOnClickListener {
            createBrowserIntent()
        }

    }

    private fun createBrowserIntent() {
        val urlAgreement = getString(R.string.url_agreement)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(urlAgreement)
        startActivity(intent)
    }

    private fun createMessageIntent() {

        val messageTitle = getString(R.string.message_title)
        val messageBody = getString(R.string.message_body)
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_TEXT, messageBody)
        intent.putExtra(Intent.EXTRA_SUBJECT, messageTitle)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.dev_email)))
        startActivity(intent)

    }

    private fun createShareIntent() {
        val url = getString(R.string.url_android_practicum)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, url)
        intent.type = "text/plain"
        val chooser = Intent.createChooser(intent, getString(R.string.text_for_chooser))
        startActivity(chooser)
    }


}

