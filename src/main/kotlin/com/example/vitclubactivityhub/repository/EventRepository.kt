package com.example.vitclubactivityhub.repository

import com.example.vitclubactivityhub.model.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long>
