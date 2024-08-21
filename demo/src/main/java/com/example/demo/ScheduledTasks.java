package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class ScheduledTasks {
    @Scheduled(cron = "*/3 * * * * *") // Cấu hình để chạy mỗi 3 giây
    @SchedulerLock(name = "TaskScheduler_scheduledTask",
            lockAtLeastFor = "PT57S", // Khóa ít nhất 57 giây
            lockAtMostFor = "PT1M") // Khóa tối đa 1 phút
    public void scheduledTask() {
        log.info("TaskScheduler.scheduledTask - Task executed");
    }
}