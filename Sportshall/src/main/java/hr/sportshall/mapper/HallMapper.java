package hr.sportshall.mapper;

import hr.sportshall.dto.HallDto;
import hr.sportshall.model.Hall;

public class HallMapper {

	private HallMapper() {

	}

	public static HallDto mapToDto(Hall hall, HallDto hallDto) {

		if (hallDto == null) {
			hallDto = new HallDto();
		}
		hallDto.setId(hall.getId());
		hallDto.setName(hall.getName());
		hallDto.setType(hall.getType());

		return hallDto;
	}

	public static Hall mapToModel(HallDto hallDto, Hall hall) {

		if (hall == null) {
			hall = new Hall();
		}
		hall.setId(hallDto.getId());
		hall.setName(hallDto.getName());
		hall.setType(hallDto.getType());

		return hall;
	}

}
