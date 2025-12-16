package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.chasacademy.databaser.coursesystem.models.Room;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findByName(String name);
    List<Room> findByCapacityGreaterThan(int capacity);
}
