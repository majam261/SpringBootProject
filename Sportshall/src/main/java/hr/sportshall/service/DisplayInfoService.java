package hr.sportshall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import hr.sportshall.displayinfo.IDisplayInfo;

@Service
public class DisplayInfoService {

	private static final Logger LOG = LoggerFactory.getLogger(DisplayInfoService.class);

	IDisplayInfo iDisplayInfo;

	@Autowired
	public void setDisplayInfo(IDisplayInfo iDisplayInfo) {
		this.iDisplayInfo = iDisplayInfo;
	}

	@Scheduled(initialDelay = 1000, fixedDelay = 86400000)
	public void run() {
		String info = "Basketball hall info";
		String displayInfo = iDisplayInfo.displayInfo(info);
		LOG.info("{}", displayInfo);
	}

}
