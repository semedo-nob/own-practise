package com.example.taskapp

import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Mixpanel
        MixpanelHelper.initializeMixpanel(applicationContext)

        // Example usage of MixpanelHelper
        val eventProperties = mapOf("category" to "sports", "location" to "stadium")
        MixpanelHelper.trackEvent("EventViewed", eventProperties)

        // Example usage of EventProvider
        val eventValues = ContentValues().apply {
            put(EventContract.EventEntry.COLUMN_NAME_TITLE, "Sample Event")
            put(EventContract.EventEntry.COLUMN_NAME_DATE, "2024-03-01")
            put(EventContract.EventEntry.COLUMN_NAME_LOCATION, "Sample Location")
        }
        val uri = contentResolver.insert(EventContract.EventEntry.CONTENT_URI, eventValues)
        uri?.let {
            // Event inserted successfully
        }
    }
}
