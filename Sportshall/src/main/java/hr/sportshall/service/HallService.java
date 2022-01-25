package hr.sportshall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.sportshall.dto.HallDto;
import hr.sportshall.mapper.HallMapper;
import hr.sportshall.model.Hall;
import hr.sportshall.repository.HallRepository;

@Service
public class HallService {

	@Autowired
	private HallRepository hallRepository;

	public HallDto getById(Long id) {
		Optional<Hall> hall = hallRepository.findById(id);

		HallDto hallDto;
		if (hall.isEmpty()) {
			return null;
		} else {
			hallDto = HallMapper.mapToDto(hall.get(), new HallDto());
		}
		return hallDto;
	}

	public List<HallDto> get() {
		List<Hall> hallList = hallRepository.findAll();

		List<HallDto> hallListDto = new ArrayList<>();
		for (Hall h : hallList) {
			hallListDto.add(HallMapper.mapToDto(h, new HallDto()));
		}
		return hallListDto;
	}

	public void save(HallDto hall) {
		Hall a = HallMapper.mapToModel(hall, new Hall());
		hallRepository.save(a);
	}

	public void update(HallDto hall) {
		Long id = hall.getId();

		if (hallRepository.findById(id).isPresent()) {
			Hall existingHall = hallRepository.findById(id).get();

			if (hall.getName() != null) {
				existingHall.setName(hall.getName());
			}
			if (hall.getType() != null) {
				existingHall.setType(hall.getType());
			}

			hallRepository.save(existingHall);
		}
	}
}
