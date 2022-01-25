package hr.sportshall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.sportshall.model.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

}
