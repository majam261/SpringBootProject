package hr.sportshall.controller;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hr.sportshall.service.DBDataInitService;

@RestController
@RequestMapping(value = "/demo", produces = "application/json")
public class InitDataController {
	
	private static final Logger LOG = LoggerFactory.getLogger(InitDataController.class);
	
	@Autowired
	private DBDataInitService appointmentServiceInit;

	
	@GetMapping("/generatedbdata")
	public void generateDummyAppointments() throws ServiceException {
		LOG.info("test");
		
		try {
			appointmentServiceInit.generateDbData();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
