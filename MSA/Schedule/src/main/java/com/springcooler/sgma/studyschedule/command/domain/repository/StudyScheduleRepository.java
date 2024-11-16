package com.springcooler.sgma.studyschedule.command.domain.repository;

import com.springcooler.sgma.studyschedule.command.domain.aggregate.StudySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StudyScheduleRepository extends JpaRepository<StudySchedule, Long> {
    List<StudySchedule> findByScheduleEndTimeBetween(Timestamp startTime, Timestamp endTime);
}
