package com.example.taskapp

// EventContract.kt

import android.net.Uri
import android.provider.BaseColumns

object EventContract {
    private const val CONTENT_AUTHORITY = "com.example.task.app"
    val BASE_CONTENT_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

    object EventEntry : BaseColumns {
        private const val TABLE_NAME = "events"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DATE = "date"
        const val COLUMN_NAME_LOCATION = "location"

        val CONTENT_URI: Uri = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build()
    }
}
