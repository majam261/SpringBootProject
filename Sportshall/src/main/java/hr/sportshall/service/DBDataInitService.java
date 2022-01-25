package hr.sportshall.service;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import hr.sportshall.dto.AppointmentDto;
import hr.sportshall.dto.HallDto;

@Service
public class DBDataInitService {

	private static final Logger LOG = LoggerFactory.getLogger(DBDataInitService.class);

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private HallService hallService;

	@Async
	public void generateDbData() {
		LOG.info("generateDbData - async method example");

		HallDto hall1 = new HallDto();
		hall1.setId(1L);
		hall1.setName("football hall 1");
		hall1.setType("football hall");

		hallService.save(hall1);

		HallDto hall2 = new HallDto();
		hall2.setId(2L);
		hall2.setName("basketball hall 1");
		hall2.setType("basketball hall");

		hallService.save(hall2);

		HallDto hall3 = new HallDto();
		hall3.setId(3L);
		hall3.setName("basketball hall 2");
		hall3.setType("basketball hall");

		hallService.save(hall3);

		AppointmentDto appointment1 = new AppointmentDto();
		appointment1.setId(1L);
		appointment1.setName("football 1");
		appointment1.setDateFrom(Timestamp.valueOf("2022-01-23 16:00:00.0"));
		appointment1.setDateTo(Timestamp.valueOf("2022-01-23 17:00:00.0"));
		appointment1.setHall(hall1);

		appointmentService.save(appointment1);

		AppointmentDto appointment2 = new AppointmentDto();
		appointment2.setId(2L);
		appointment2.setName("basketball 1");
		appointment2.setDateFrom(Timestamp.valueOf("2022-01-24 20:00:00.0"));
		appointment2.setDateTo(Timestamp.valueOf("2022-01-24 21:00:00.0"));
		appointment2.setHall(hall2);

		appointmentService.save(appointment2);
	}

}
