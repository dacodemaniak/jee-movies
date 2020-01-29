package cinema.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistance.entity.Actor;
public interface ActorRepository extends JpaRepository<Actor, Integer>{

}
