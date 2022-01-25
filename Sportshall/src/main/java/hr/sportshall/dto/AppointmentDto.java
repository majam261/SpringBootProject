package hr.sportshall.dto;

import java.sql.Timestamp;

public class AppointmentDto {

	private Long id;

	private String name;

	private Timestamp dateFrom;

	private Timestamp dateTo;

	private HallDto hall;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Timestamp getDateTo() {
		return dateTo;
	}

	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}

	public HallDto getHall() {
		return hall;
	}

	public void setHall(HallDto hall) {
		this.hall = hall;
	}
}
