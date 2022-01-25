package hr.sportshall.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hr.sportshall.controller.AppointmentController;
import hr.sportshall.service.AppointmentService;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class SportshallApplicationTests {

	
	@Autowired
  	private AppointmentService appointmentService;

    @Autowired
    private AppointmentController appointmentController;
	    
	    
	@Test
	void contextLoads() {
		
		 assertThat(appointmentService, notNullValue());
		 assertThat(appointmentController, notNullValue());
	}

}
