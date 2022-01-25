package hr.sportshall.mapper;

import hr.sportshall.dto.AppointmentDto;
import hr.sportshall.dto.HallDto;
import hr.sportshall.model.Appointment;
import hr.sportshall.model.Hall;

public class AppointmentMapper {

	private AppointmentMapper() {
	}

	public static AppointmentDto mapToDto(Appointment appointment, AppointmentDto appointmentDto) {

		if (appointmentDto == null) {
			appointmentDto = new AppointmentDto();
		}
		appointmentDto.setId(appointment.getId());
		appointmentDto.setName(appointment.getName());
		appointmentDto.setDateFrom(appointment.getDateFrom());
		appointmentDto.setDateTo(appointment.getDateTo());

		if (appointment.getHall() != null && appointment.getHall().getId() != null) {
			HallDto hallDto = HallMapper.mapToDto(appointment.getHall(), new HallDto());
			appointmentDto.setHall(hallDto);
		}
		return appointmentDto;
	}

	public static Appointment mapToModel(AppointmentDto appointmentDto, Appointment appointment) {

		if (appointment == null) {
			appointment = new Appointment();
		}
		appointment.setId(appointmentDto.getId());
		appointment.setName(appointmentDto.getName());
		appointment.setDateFrom(appointmentDto.getDateFrom());
		appointment.setDateTo(appointmentDto.getDateTo());

		if (appointmentDto.getHall() != null && appointmentDto.getHall().getId() != null) {
			Hall hall = HallMapper.mapToModel(appointmentDto.getHall(), new Hall());
			appointment.setHall(hall);
		}

		return appointment;
	}

}
