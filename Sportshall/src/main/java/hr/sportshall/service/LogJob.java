package hr.sportshall.service;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogJob {

	private static final Logger LOG = LoggerFactory.getLogger(LogJob.class);

	@Scheduled(fixedRate = 30000)
	private void process() {
		LOG.info(Calendar.getInstance().getTime().toString());
	}
}
