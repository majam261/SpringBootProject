package hr.sportshall.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import hr.sportshall.dto.AppointmentDto;
import hr.sportshall.service.AppointmentService;

@RestController
@RequestMapping(value = "/appointment", produces = "application/json")
public class AppointmentController {

	private static final Logger LOG = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/get/id/{id}")
	public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable("id") Long id) {
		LOG.info("getAppointmentById, id: {}", id);

		AppointmentDto appointment = appointmentService.getById(id);
		return ResponseEntity.ok(appointment);
	}

	@GetMapping("/get")
	public List<AppointmentDto> getAppointments() {
		LOG.info("getAppointments");

		return appointmentService.get();
	}

	@PostMapping("/create")
	public ResponseEntity<String> newAppointment(@RequestBody AppointmentDto appointment) {
		LOG.info("newAppointment");

		if (appointment == null || appointment.getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No appointment ID set");
		}
		appointmentService.save(appointment);

		String appointmentXML = appointmentToXml(appointment);
		writeXmlToFile(appointmentXML);

		return ResponseEntity.ok("OK");
	}

	@PatchMapping("/update")
	public ResponseEntity<String> updateAppointment(@RequestBody AppointmentDto appointment) {
		LOG.info("updateAppointment");

		if (appointment == null || appointment.getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No appointment ID set");
		}
		appointmentService.update(appointment);
		return ResponseEntity.ok("OK");
	}

	private static String appointmentToXml(AppointmentDto appointment) {

		String appointmentXml = "";
		try {
			JacksonXmlModule module = new JacksonXmlModule();
			XmlMapper xmlMapper = new XmlMapper(module);
			xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
			appointmentXml = xmlMapper.writeValueAsString(appointment);
			LOG.info(appointmentXml);
		} catch (JsonProcessingException e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
		}
		return appointmentXml;
	}

	private static void writeXmlToFile(String xml) {

		try {
			File xmlOutput = new File(".\\logs\\xml_requests\\xml_request.xml");
			FileWriter fileWriter = new FileWriter(xmlOutput);

			fileWriter.write(xml);
			fileWriter.close();
		} catch (IOException e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
		}
	}
}
