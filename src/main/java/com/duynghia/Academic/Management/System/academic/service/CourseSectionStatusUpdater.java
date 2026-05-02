package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.enums.CourseSectionStatus;
import com.duynghia.Academic.Management.System.academic.repository.CourseSectionRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseSectionStatusUpdater {

    CourseSectionRepository repository;

    //    @Scheduled(cron = "0/10 * * * * *")
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateCourseSectionStatuses() {
        LocalDate today = LocalDate.now();
        repository.updateStatusToOngoing(CourseSectionStatus.ONGOING, CourseSectionStatus.PLANNED, today);
        repository.updateStatusToCompleted(CourseSectionStatus.COMPLETED, CourseSectionStatus.ONGOING, today);
    }
}
