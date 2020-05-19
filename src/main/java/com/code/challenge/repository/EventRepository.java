package com.code.challenge.repository;

import com.code.challenge.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}