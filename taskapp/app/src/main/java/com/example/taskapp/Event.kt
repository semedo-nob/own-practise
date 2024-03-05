package com.example.taskapp

data class Event(
    val id: Long,
    val name: String,
    val date: String,
    val location: String,
    val attendees: List<String>
)
