package com.example.vitclubactivityhub.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Event(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    val clubName: String,

    val organizerInfo: String,

    val location: String,

    val dateTime: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "created_by")
    val createdBy: User // Club who created the event
)
