package hr.sportshall.controller;

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

import hr.sportshall.dto.HallDto;
import hr.sportshall.service.HallService;

@RestController
@RequestMapping(value = "/hall", produces = "application/json")
public class HallController {

	private static final Logger LOG = LoggerFactory.getLogger(HallController.class);

	@Autowired
	private HallService hallService;

	@GetMapping("/get/id/{id}")
	public ResponseEntity<HallDto> getHallById(@PathVariable("id") Long id) {
		LOG.info("getHallById, id: {}", id);

		HallDto hall = hallService.getById(id);
		return ResponseEntity.ok(hall);
	}

	@GetMapping("/get")
	public List<HallDto> getHalls() {
		LOG.info("getHalls");

		return hallService.get();
	}

	@PostMapping("/create")
	public ResponseEntity<String> newHall(@RequestBody HallDto hall) {
		LOG.info("newHall");

		if (hall == null || hall.getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hall ID set");
		}
		hallService.save(hall);
		return ResponseEntity.ok("OK");
	}

	@PatchMapping("/update")
	public ResponseEntity<String> updateHall(@RequestBody HallDto hall) {
		LOG.info("updateHall");

		if (hall == null || hall.getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hall ID set");
		}
		hallService.save(hall);

		return ResponseEntity.ok("OK");
	}
}
