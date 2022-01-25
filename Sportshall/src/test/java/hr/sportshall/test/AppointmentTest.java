package hr.sportshall.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import hr.sportshall.dto.AppointmentDto;
import hr.sportshall.service.AppointmentService;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentTest {

	@Autowired
	private MockMvc mockMvc;
	
  	@Autowired
  	private ObjectMapper objectMapper;

  	@Autowired
  	private AppointmentService appointmentService;
  	
  	@Test
  	void getAppointmentTest()
  	  throws Exception {

  		AppointmentDto appointment = new AppointmentDto();
  		appointment.setId(501L);
  		appointment.setName("kosarka 501");
  		appointment.setDateFrom(Timestamp.valueOf("2022-01-22 16:00:00.0"));
		appointment.setDateTo(Timestamp.valueOf("2022-01-22 17:00:00.0"));
  		
		appointmentService.save(appointment);

		mockMvc.perform(get("/appointment/get/id/501")
  	      .contentType(MediaType.APPLICATION_JSON))
  	      .andExpect(status().isOk())
  	      .andExpect(content()
  	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
  	      .andExpect(MockMvcResultMatchers.jsonPath("id").value(501L))
  	      .andExpect(MockMvcResultMatchers.jsonPath("name").value("kosarka 501"));
  	}
  	
  	
  	@Test
  	void postAppointmentTest()
  	  throws Exception {

  		AppointmentDto appointment = new AppointmentDto();
  		appointment.setId(502L);
  		appointment.setName("kosarka 502");
  		appointment.setDateFrom(Timestamp.valueOf("2022-01-28 16:00:00.0"));
		appointment.setDateTo(Timestamp.valueOf("2022-01-28 17:00:00.0"));
  		
		appointmentService.save(appointment);

		mockMvc.perform(post("/appointment/create")
  	            .contentType("application/json")
  	            .content(objectMapper.writeValueAsString(appointment)))
  	            .andExpect(status().isOk());
		
		AppointmentDto retAppointment = appointmentService.getById(502L);
		assertThat(retAppointment.getId(), equalTo(appointment.getId()));
		assertThat(retAppointment.getName(), equalTo(appointment.getName()));
		assertThat(retAppointment.getDateFrom(), equalTo(appointment.getDateFrom()));
		assertThat(retAppointment.getDateTo(), equalTo(appointment.getDateTo()));
  	}
}
