package hr.sportshall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.sportshall.dto.AppointmentDto;
import hr.sportshall.mapper.AppointmentMapper;
import hr.sportshall.model.Appointment;
import hr.sportshall.model.Hall;
import hr.sportshall.repository.AppointmentRepository;
import hr.sportshall.repository.HallRepository;

@Service
public class AppointmentService {

	private static final Logger LOG = LoggerFactory.getLogger(AppointmentService.class);

	@Autowired
	private AppointmentRepository appointmentRepository;
		
	@Autowired
	private HallRepository hallRepository;

	public AppointmentDto getById(Long id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		
		AppointmentDto appointmentDto;
		if (appointment.isEmpty()) {
			return null;
		} else {
			appointmentDto = AppointmentMapper.mapToDto(appointment.get(), new AppointmentDto());
		}
		return appointmentDto;
	}

	public List<AppointmentDto> get() {
		LOG.info("getAppointments - non async method");
		List<Appointment> appointmentList = appointmentRepository.findAll();
		
		List<AppointmentDto> appointmentListDto = new ArrayList<>();
		for (Appointment a:appointmentList) {
			appointmentListDto.add(AppointmentMapper.mapToDto(a, new AppointmentDto()));
		}
		return appointmentListDto;
	}

	public void save(AppointmentDto appointmentDto) {
		Appointment a = AppointmentMapper.mapToModel(appointmentDto, new Appointment());
		appointmentRepository.save(a);
	}
	
	public void update(AppointmentDto appointment) {
		
		Long id = appointment.getId();
		
		if (appointmentRepository.findById(id).isPresent()){
			Appointment existingAppointment = appointmentRepository.findById(id).get();

			if (appointment.getName() != null) {
				existingAppointment.setName(appointment.getName());
			}
			if (appointment.getDateFrom() != null) {
				existingAppointment.setDateFrom(appointment.getDateFrom());
			}
			if (appointment.getDateTo() != null) {
				existingAppointment.setDateTo(appointment.getDateTo());
			}
			
			if (appointment.getHall() != null && appointment.getHall().getId()!= null) {
				Hall hall = hallRepository.getById(appointment.getHall().getId());
				existingAppointment.setHall(hall);
			}
			appointmentRepository.save(existingAppointment);
	     }
	}
}
