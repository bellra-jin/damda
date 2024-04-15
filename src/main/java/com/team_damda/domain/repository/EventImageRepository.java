package com.team_damda.domain.repository;

import com.team_damda.domain.entity.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventImageRepository extends JpaRepository<EventImage,Long> {
}
