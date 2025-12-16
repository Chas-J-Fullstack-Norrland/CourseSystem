package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.chasacademy.databaser.coursesystem.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
