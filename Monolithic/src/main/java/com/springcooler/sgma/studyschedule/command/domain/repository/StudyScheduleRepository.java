package com.springcooler.sgma.studyschedule.command.domain.repository;

import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyScheduleRepository extends JpaRepository<StudySchedule, Long> {
}
