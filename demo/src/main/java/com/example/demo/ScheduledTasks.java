package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
public class ScheduledTasks {

    private final MetricDataRepository metricDataRepository;

    @Autowired
    public ScheduledTasks(MetricDataRepository metricDataRepository) {
        this.metricDataRepository = metricDataRepository;
    }

    @Scheduled(cron = "*/10 * * * * *") // Chạy mỗi 10 giây
    @SchedulerLock(name = "TaskScheduler_updateMetricData",
          lockAtLeastFor = "PT20S",
          lockAtMostFor = "PT30S")
    public void updateMetricData() {
        log.info("Attempting to update metric data...");

        Optional<MetricData> dataOpt = metricDataRepository.findById(1L);
        MetricData data = dataOpt.orElse(new MetricData());
        data.setMetricValue(data.getMetricValue() + 1);
        data.setUpdatedAt(LocalDateTime.now());

        metricDataRepository.save(data);

        log.info("MetricData updated to value: {}", data.getMetricValue());
    }
}
