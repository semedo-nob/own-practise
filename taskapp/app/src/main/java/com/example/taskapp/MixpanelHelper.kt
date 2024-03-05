package com.example.taskapp

import android.content.Context
import com.mixpanel.android.mpmetrics.MixpanelAPI

object MixpanelHelper {
    private const val MIXPANEL_TOKEN = "YOUR_MIXPANEL_TOKEN" // Replace with your Mixpanel token
    private lateinit var mixpanel: MixpanelAPI

    fun initializeMixpanel(context: Context) {
        mixpanel = MixpanelAPI.getInstance(context, MIXPANEL_TOKEN)
    }

    fun trackEvent(eventName: String, properties: Map<String, Any>) {
        mixpanel.trackMap(eventName, properties)
    }
}
